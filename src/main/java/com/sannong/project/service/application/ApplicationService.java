package com.sannong.project.service.application;

import com.sannong.project.domain.application.Application;
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

    public void createApplication(CreateApplicationCommand createApplicationCommand) {

        User user = createApplicationCommand.getUser();

        // Add user
        String password = PasswordGenerator.generatePassword(6);
        String mobilePhone = user.getMobilePhone();
        String encryptedPassword = PasswordGenerator.encryptPassword(password, mobilePhone);
        user.setPassword(encryptedPassword);
        user.setUserName(mobilePhone);
        Timestamp creationTime = new Timestamp(System.currentTimeMillis());
        user.setCreationTime(creationTime);
        user.setLastUpdated(creationTime);
        userRepository.save(user);

        // Add authorities
        Authority authority = new Authority();
        authority.setUserName(mobilePhone);
        authority.setAuthority(RoleType.ROLE_USER.toString());
        authorityRepository.save(authority);

        // Add application
        Application application = new Application();
        application.setCreationTime(creationTime);
        application.setUser(user);

        // Add questionnaire
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAnswers(StringUtils.join(createApplicationCommand.getAnswers(), ','));
        questionnaire.setQuestionnaireNumber(1);
        questionnaire.setQuestionnaireCommitted(true);
        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnaire);

        application.setQuestionnaires(questionnaires);
        applicationRepository.save(application);



        /*

        // Add application info
        Application application = new Application();
        application.setUser(user);
        application.setCreationTime(creationTime);
        applicationRepository.addProjectApplicationInfo(application);

        // Add questionnaires info
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setApplicationId(application.getApplicationId());
        questionnaire.setQuestionnaireNumber(1);
        questionnaire.setQuestionnaireCommitted(true);
        questionnaire.setCreationTime(creationTime);
        questionnaire.setLastUpdated(creationTime);
        questionnaire.setAnswers(application.getQuestionnaires().get(0).getAnswers());
        //questionnaire.setConcatenatedAnswers(questionnaire.getConcatenatedAnswers());
        applicationRepository.addQuestionnaire(questionnaire);
        */

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
