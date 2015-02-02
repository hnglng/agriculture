package com.sannong.project.service.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * application service
 *
 * @author William Zhang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplicationService {

    /*
    private static final Logger logger = Logger.getLogger(ApplicationService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private RegionFactory regionFactory;
    @Autowired
    private MailContentFactory mailContentFactory;
    @Autowired
    private MailAsyncSender mailAsyncSender;

    public List<Application> findByUserName(String userName){
        return applicationRepository.findByUserName(userName);
    }

    public void createApplication(CreateApplicationCommand createApplicationCommand) {

        User user = createApplicationCommand.getUser();

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
        userRepository.addUserAuthority(authorityMap);

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

        // Send email to admin
        Region region = regionFactory.build(user.getCompanyProvince(), user.getCompanyCity(), user.getCompanyDistrict());
        String realName = user.getRealName();
        String timeOfSubmission = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(creationTime);
        sendMailToAdmin(region, realName, timeOfSubmission, mobilePhone);

        // Send sms message to user
        smsService.sendLoginMessage(mobilePhone, password);
    }

    private void sendMailToAdmin(Region region, String applicantName, String timeOfSubmission, String cellphone) {

        String mailContent = mailContentFactory.build(region, applicantName, timeOfSubmission, cellphone);
        mailAsyncSender.sendMail(mailContent);
    }

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
