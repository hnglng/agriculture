package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.ApplicationSpecification;
import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.domain.common.Status;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.presentation.dto.Response;
import com.sannong.project.service.application.ApplicationService;
import com.sannong.project.service.sms.ISmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
@RequestMapping(value="/project-application")
public class ApplicationController {

    private static final String APPLICATION_PAGE = "project-application";
    /*
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ISmsService smsService;

    @Qualifier("applicationSpecification")
    @Autowired
    private ApplicationSpecification applicationSpec;
    */

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showApplication() {
        return new ModelAndView(APPLICATION_PAGE);
    }

    /*
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createApplication(
            @ModelAttribute("projectAppForm") CreateApplicationCommand createApplicationCommand)
            throws Exception {
        Response response = new Response();
        if (applicationSpec.isSatisfiedBy(createApplicationCommand)) {
            applicationService.createApplication(createApplicationCommand);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            response.setStatusCode(Status.FAILURE.getCode());
            response.setData(applicationSpec.getErrors());
            return new ResponseEntity<Response>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @RequestMapping(value = "/questionnaires/{number}", method = RequestMethod.GET)
    public @ResponseBody
    Questionnaire getQuestionnaire(@PathVariable("number") Integer number) throws Exception{
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestions(applicationService.getQuestionsByQuestionnaireNumber(number));
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
*/

}
