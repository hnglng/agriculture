package com.sannong.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sannong.domain.project.ApplicationSpecification;
import com.sannong.domain.project.Questionnaire;
import com.sannong.domain.share.ResponseStatus;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.presentation.model.Response;
import com.sannong.service.ISmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sannong.domain.project.Application;
import com.sannong.service.IProjectService;


/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
public class ProjectController {
    private static final String APPLICATION_PAGE = "project-application";
    private static final String COMPLETION_PAGE = "project-application-completion";

    @Autowired
    private IProjectService projectApplicationService;
    @Autowired
    private ISmsService smsService;
    @Qualifier("applicationSpecification")
    @Autowired
    private ApplicationSpecification applicationSpec;


    @RequestMapping(value="project-application", method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView(APPLICATION_PAGE);
    }

    @RequestMapping(value="project-application", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@ModelAttribute("projectAppForm") Application application) throws Exception {
        Response response = new Response();
        if (applicationSpec.isSatisfiedBy(application)) {
            projectApplicationService.addApplication(application);
            response.setStatusCode(ResponseStatus.OK.getCode());
            response.setURI("project-application-completion");
        }else{
            response.setStatusCode(ResponseStatus.FAILURE.getCode());
            response.setData(applicationSpec.getUnsatisfiedReasons());
        }
        return response;
    }

    @RequestMapping(value = "project-application-completion", method = RequestMethod.GET)
    public ModelAndView showCompletion() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("project-application-completion", new Object());
        return new ModelAndView(COMPLETION_PAGE, models);
    }

    @RequestMapping(value = "project-application/questionnaire/{number}", method = RequestMethod.GET)
    public @ResponseBody
    Questionnaire getQuestionnaire(@PathVariable("number") Integer number) throws Exception{
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestions(projectApplicationService.getQuestionsByQuestionnaireNumber(number));
        return questionnaire;
    }

    @RequestMapping(value = "project-application/captcha",method = RequestMethod.POST)
    public @ResponseBody Response sendCaptchaCode(HttpServletRequest request){
        String mobilePhone = request.getParameter("user.mobilePhone");
        if (StringUtils.isBlank(mobilePhone)){
            mobilePhone = request.getParameter("mobilePhone");
        }
        Response response = new Response();
        if (applicationSpec.isSatisfiedBy(mobilePhone)) {
            String captcha = PasswordGenerator.generateValidationCode(4);
            String result = smsService.sendValidationCode(mobilePhone, captcha);
            if (StringUtils.isNotBlank(result)){
                response.setStatusCode(ResponseStatus.CAPTCHA_SENT.getCode());
                response.setStatusMessage(ResponseStatus.CAPTCHA_SENT.getMessage());
            }else{
                response.setStatusCode(ResponseStatus.CAPTCHA_UNSENT.getCode());
                response.setStatusMessage(ResponseStatus.CAPTCHA_UNSENT.getMessage());
            }
        }else{
            response.setStatusCode(ResponseStatus.FAILURE.getCode());
            response.setStatusMessage(ResponseStatus.FAILURE.getMessage());
            response.setData(applicationSpec.getUnsatisfiedReasons());
        }
        return response;
    }

}
