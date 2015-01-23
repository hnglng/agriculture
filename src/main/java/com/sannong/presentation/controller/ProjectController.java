package com.sannong.presentation.controller;

import com.sannong.domain.common.Status;
import com.sannong.domain.project.ApplicationSpecification;
import com.sannong.domain.project.Questionnaire;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.presentation.command.CreateApplicationCommand;
import com.sannong.presentation.dto.Response;
import com.sannong.service.project.IProjectService;
import com.sannong.service.sms.ISmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
@RequestMapping(value="/project-application")
public class ProjectController {
    private static final String APPLICATION_PAGE = "project-application";
    private static final String COMPLETION_PAGE = "project-application-completion";

    @Autowired
    private IProjectService projectService;
    @Autowired
    private ISmsService smsService;
    @Qualifier("applicationSpecification")
    @Autowired
    private ApplicationSpecification applicationSpec;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showApplication() {
        return new ModelAndView(APPLICATION_PAGE);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createApplication(@RequestBody CreateApplicationCommand createApplicationCommand)
            throws Exception {
        Response response = new Response();
        if (applicationSpec.isSatisfiedBy(createApplicationCommand)) {
            projectService.createApplication(createApplicationCommand);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            response.setStatusCode(Status.FAILURE.getCode());
            response.setData(applicationSpec.getErrors());
            return new ResponseEntity<Response>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/completion", method = RequestMethod.GET)
    public ModelAndView showCompletion() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("project-application-completion", new Object());
        return new ModelAndView(COMPLETION_PAGE, models);
    }

    @RequestMapping(value = "/questionnaires/{number}", method = RequestMethod.GET)
    public @ResponseBody
    Questionnaire getQuestionnaire(@PathVariable("number") Integer number) throws Exception{
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestions(projectService.getQuestionsByQuestionnaireNumber(number));
        return questionnaire;
    }

    @RequestMapping(value = "/captcha",method = RequestMethod.POST)
    public @ResponseBody Response sendCaptchaCode(HttpServletRequest request){
        String mobilePhone = request.getParameter("user.mobilePhone");
        if (StringUtils.isBlank(mobilePhone)){
            mobilePhone = request.getParameter("mobilePhone");
        }
        Response response = new Response();

        if (StringUtils.isBlank(mobilePhone)) {
            response.setStatusCode(Status.CELLPHONE_IS_NULL.getCode());
            response.setStatusMessage(Status.CELLPHONE_IS_NULL.getMessage());
            return response;
        }

        String captcha = PasswordGenerator.generateValidationCode(4);
        String result = smsService.sendValidationCode(mobilePhone, captcha);
        if (StringUtils.isNotBlank(result)){
            response.setStatusCode(Status.CAPTCHA_SENT.getCode());
            response.setStatusMessage(Status.CAPTCHA_SENT.getMessage());
        }else{
            response.setStatusCode(Status.FAILURE.getCode());
            response.setStatusMessage(Status.FAILURE.getMessage());
            response.setData(applicationSpec.getErrors());
        }
        return response;
    }

}
