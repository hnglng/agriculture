package com.sannong.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sannong.domain.applications.*;
import com.sannong.domain.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.mail.MailContentFactory;
import com.sannong.domain.region.RegionFactory;
import com.sannong.domain.region.Region;
import com.sannong.domain.user.RoleType;
import com.sannong.infrastructure.mail.MailAsyncSender;
import com.sannong.domain.user.AuthorityRepository;
import com.sannong.domain.user.UserRepository;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.service.IProjectService;
import com.sannong.service.ISmsService;


/**
 * project service
 *
 * @author William Zhang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements IProjectService {

    private static final Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private RegionFactory regionFactory;
    @Autowired
    private MailContentFactory mailContentFactory;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MailAsyncSender mailAsyncSender;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Update answers to answer fields relatively
     *
     * @param answer
     * @author William Zhang
     */
    private void setAnswers(Answer answer) {

        int questionnaireNo = answer.getQuestionnaireNo();
        StringBuffer sb = new StringBuffer();

        for (String answerString : answer.getAnswers()) {
            sb.append(answerString + ";");
        }
        String answers = sb.toString().substring(0,
                sb.toString().length() - 1);

        switch (questionnaireNo) {
            case 1:
                answer.setQuestionnaire1Answers(answers);
                break;
            case 2:
                answer.setQuestionnaire2Answers(answers);
                break;
            case 3:
                answer.setQuestionnaire3Answers(answers);
                break;
            case 4:
                answer.setQuestionnaire4Answers(answers);
                break;
            case 5:
                answer.setQuestionnaire5Answers(answers);
                break;
            default:
                answer.setQuestionnaire1Answers(answers);
                break;
        }
    }

    public boolean updateAnswersAndComment(Answer answer) throws Exception {

        boolean result = true;
        setAnswers(answer);
        try {
            answerRepository.updateAnswers(answer);
            
            if (answer.getComment() != null && answer.getComment().getContent() != null){
            	Timestamp createTime = new Timestamp(System.currentTimeMillis());
            	answer.getComment().setCreateTime(createTime);
            	commentRepository.addComment(answer);
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public void sendMailToAdmin(Region region, String applicantName, String timeOfSubmission, String cellphone) {

        String mailContent = mailContentFactory.build(region, applicantName, timeOfSubmission, cellphone);
        mailAsyncSender.sendMail(mailContent);
    }

    public void makeApplication(Application application) {

        User user = application.getUser();

        // Add user info
        String password = PasswordGenerator.generatePassword(6);
        String mobilePhone = user.getMobilePhone();
        String encryptedPassword = PasswordGenerator.encryptPassword(password, mobilePhone);
        user.setPassword(encryptedPassword);
        user.setUserName(mobilePhone);
        Timestamp creationTime = new Timestamp(System.currentTimeMillis());
        user.setCreationTime(creationTime);
        user.setLastUpdated(creationTime);
        userRepository.addUser(user);

        // Add authorities
        Map<String, Object> authorityMap = new HashMap<String, Object>();
        authorityMap.put("userName", mobilePhone);
        authorityMap.put("authority", RoleType.ROLE_USER.toString());
        authorityRepository.addUserAuthority(authorityMap);

        // Add application info
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
        questionnaire.setConcatenatedAnswers(questionnaire.getConcatenatedAnswers());
        questionnaireRepository.addQuestionnaire(questionnaire);

        // Send email to admin
        Region region = regionFactory.build(user.getCompanyProvince(), user.getCompanyCity(), user.getCompanyDistrict());
        String realName = user.getRealName();
        String timeOfSubmission = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(creationTime);
        sendMailToAdmin(region, realName, timeOfSubmission, mobilePhone);

        // Send sms message to user
        smsService.sendLoginMessage(mobilePhone, password);

    }

    public List<Question> getQuestionsByQuestionnaireNumber(Integer number) {
        List<Question> questions = questionnaireRepository.findQuestionsByQuestionnaireNumber(number);
        return questions;
    }

    public int getTotalQuestions() {
        return questionRepository.getTotalQuestions();
    }

    public Application getApplicationBy(String userName){
        return applicationRepository.getApplicationBy(userName);
    }

    @Override
    public Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber) {
        return applicationRepository.getQuestionnaireBy(applicationId, questionnaireNumber);
    }


}
