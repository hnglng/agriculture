package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.application.ApplicationResource;
import com.sannong.project.domain.application.ApplicationResourceAssembler;
import com.sannong.project.domain.application.ApplicationSpecification;
import com.sannong.project.domain.common.Status;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.presentation.dto.Response;
import com.sannong.project.service.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


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

    @RequestMapping(value="/application/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> readApplication(@PathVariable Long userId){
        Application application = applicationService.findByUserId(userId);
        return new ResponseEntity<Application>(application, HttpStatus.OK);
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
