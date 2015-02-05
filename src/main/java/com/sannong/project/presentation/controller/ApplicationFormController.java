package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.ApplicationSpecification;
import com.sannong.project.domain.common.Status;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.presentation.dto.Response;
import com.sannong.project.service.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
@RequestMapping(value="/application-form")
public class ApplicationFormController {
    private static final String APPLICATION_FORM_PAGE = "application-form";

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationSpecification applicationSpec;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showApplication() {
        return new ModelAndView(APPLICATION_FORM_PAGE);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createApplication(
            @ModelAttribute("projectAppForm") CreateApplicationCommand createApplicationCommand){
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

}
