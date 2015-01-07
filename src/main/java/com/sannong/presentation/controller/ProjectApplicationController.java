package com.sannong.presentation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sannong.domain.applications.Question;
import com.sannong.domain.applications.Questionnaire;
import com.sannong.domain.message.ResponseStatus;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.presentation.model.Response;
import com.sannong.service.ISmsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sannong.domain.applications.Application;
import com.sannong.service.IProjectService;
import com.sannong.service.IUserService;
import com.sannong.service.IValidationService;


/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
@RequestMapping(value = "project-application")
public class ProjectApplicationController {
	private static final Logger logger = Logger.getLogger(ProjectApplicationController.class);
    private static final String PROJECT_APPLICATION_COMPLETION_PAGE = "project-application-completion";
    private static final String PROJECT_APPLICATION_PAGE = "project-application";

    @Resource
    private IProjectService projectService;
    @Autowired
    private IValidationService validationService;
    @Autowired
    private ISmsService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView(PROJECT_APPLICATION_PAGE);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("projectAppForm") Application application) throws Exception {
        projectService.addApplication(application);
        return new ModelAndView(PROJECT_APPLICATION_COMPLETION_PAGE);
    }

    @RequestMapping(value = "project-application-completion", method = RequestMethod.GET)
    public ModelAndView showCompletion() {

        Map<String, Object> models = new HashMap<String, Object>();
        models.put("project-application-completion", new Object());
        return new ModelAndView(PROJECT_APPLICATION_COMPLETION_PAGE, models);
    }

    @RequestMapping(value = "/questionnaire/{number}", method = RequestMethod.GET)
    public @ResponseBody
    Questionnaire getQuestionnaire(@PathVariable("number") Integer number) throws Exception{
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestions(projectService.getQuestionsByQuestionnaireNumber(number));
        return questionnaire;
    }

    @RequestMapping(value = "project-application/validate-application-form", method = RequestMethod.POST)
    public @ResponseBody
    Response validateForm(HttpServletRequest request) throws IOException {
        String cellphone = request.getParameter("cellphone");
        String validationCode = request.getParameter("validationCode");

        if (validationService.validateUniqueCellphone(cellphone) == false){
            return new Response(
                    ResponseStatus.CELLPHONE_NOT_UNIQUE.getStatusCode(),
                    ResponseStatus.CELLPHONE_NOT_UNIQUE.getStatusDescription());
        }else if(validationService.validateValidationCode(cellphone, validationCode) == false){
            return new Response(
                    ResponseStatus.CAPTCHA_INCORRECT.getStatusCode(),
                    ResponseStatus.CAPTCHA_INCORRECT.getStatusDescription());
        }else{
            return new Response(
                    ResponseStatus.SUCCESS.getStatusCode(),
                    ResponseStatus.SUCCESS.getStatusDescription());

        }
    }

    @RequestMapping(value = "project-application/sendValidationCode",method = RequestMethod.POST)
    public @ResponseBody Response sendCaptchaCode(HttpServletRequest request){
        String cellphone = request.getParameter("applicant.cellphone");
        if (StringUtils.isBlank(cellphone)){
            cellphone = request.getParameter("cellphone");
        }

        if (StringUtils.isBlank(cellphone)){
            return new Response(
                    ResponseStatus.CELLPHONE_IS_NULL.getStatusCode(),
                    ResponseStatus.CELLPHONE_IS_NULL.getStatusDescription());
        }

        if (validationService.validateUniqueCellphone(cellphone) == false){
            return new Response(
                    ResponseStatus.CELLPHONE_NOT_UNIQUE.getStatusCode(),
                    ResponseStatus.CELLPHONE_NOT_UNIQUE.getStatusDescription());
        }else{
            String validationCode = PasswordGenerator.generateValidationCode(4);
            String result = smsService.sendValidationCode(cellphone, validationCode);
            if (StringUtils.isNotBlank(result)){
                return new Response(
                        ResponseStatus.CAPTCHA_WAS_SENT.getStatusCode(),
                        ResponseStatus.CAPTCHA_WAS_SENT.getStatusDescription());

            }else{
                return new Response(
                        ResponseStatus.SMS_SEND_CAPTCHA_FAILURE.getStatusCode(),
                        ResponseStatus.SMS_SEND_CAPTCHA_FAILURE.getStatusDescription());
            }

        }
    }

}
