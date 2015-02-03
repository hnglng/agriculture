package com.sannong.project.service.application;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.application.Question;
import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.domain.mail.MailContentFactory;
import com.sannong.project.domain.region.Region;
import com.sannong.project.domain.region.RegionFactory;
import com.sannong.project.domain.user.Authority;
import com.sannong.project.domain.user.RoleType;
import com.sannong.project.domain.user.User;
import com.sannong.project.infrastructure.mail.MailAsyncSender;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.AuthorityRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.UserRepository;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.service.sms.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * application service
 *
 * @author William Zhang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplicationService {
    private static final Logger logger = Logger.getLogger(ApplicationService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private RegionFactory regionFactory;
    @Autowired
    private MailContentFactory mailContentFactory;
    @Autowired
    private MailAsyncSender mailAsyncSender;

    private List<Question> buildQuestions(List<String> answers){
        List<Question> questions = new ArrayList<Question>();
        for (String answer : answers){
            String[] pair  = answer.split(":");
            Question question = new Question();
            question.setQuestionId(Long.parseLong(pair[0]));
            questions.add(question);
        }
        return questions;
    }

    public void createApplication(CreateApplicationCommand createApplicationCommand) {

        Timestamp creationTime = new Timestamp(System.currentTimeMillis());

        User user = createApplicationCommand.getUser();
        String password = PasswordGenerator.generatePassword(6);
        String mobilePhone = user.getMobilePhone();
        String encryptedPassword = PasswordGenerator.encryptPassword(password, mobilePhone);
        user.setPassword(encryptedPassword);
        user.setUserName(mobilePhone);
        user.setEnabled(1);
        user.setCreationTime(creationTime);
        user.setLastUpdated(creationTime);

        Application application = new Application();
        application.setCreationTime(creationTime);
        application.setUser(user);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAnswers(StringUtils.join(createApplicationCommand.getAnswers(), ','));
        questionnaire.setQuestionnaireNumber(1);
        questionnaire.setQuestionnaireCommitted(true);
        //questionnaire.setQuestions(buildQuestions(createApplicationCommand.getAnswers()));

        // important: for JPA, application instance has to be set into its child (questionnaire),
        //  otherwise, will throw error: application_id can not be null.
        questionnaire.setApplication(application);

        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnaire);
        application.setQuestionnaires(questionnaires);

        applicationRepository.save(application);

        Authority authority = new Authority();
        authority.setUserName(mobilePhone);
        authority.setAuthority(RoleType.ROLE_USER.toString());
        authorityRepository.save(authority);

        // Send email to admin
        Region region = regionFactory.build(user.getCompanyProvince(), user.getCompanyCity(), user.getCompanyDistrict());
        String timeOfSubmission = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(creationTime);
        sendMailToAdmin(region, user.getRealName(), timeOfSubmission, mobilePhone);

        // Send sms message to user
        smsService.sendLoginMessage(mobilePhone, password);
    }

    private void sendMailToAdmin(Region region, String applicantName, String timeOfSubmission, String cellphone) {

        String mailContent = mailContentFactory.build(region, applicantName, timeOfSubmission, cellphone);
        mailAsyncSender.sendMail(mailContent);
    }

        /*
    public List<Question> getQuestionsByQuestionnaireNumber(Integer number) {
        List<Question> questions = applicationRepository.findQuestionsByQuestionnaireNumber(number);
        return questions;
    }

    public int getTotalQuestions() {
        return applicationRepository.getTotalQuestions();
    }

    public Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber) {
        return applicationRepository.getQuestionnaireBy(applicationId, questionnaireNumber);
    }
    */


}
