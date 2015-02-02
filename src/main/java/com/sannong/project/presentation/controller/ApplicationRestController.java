package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.application.ApplicationResource;
import com.sannong.project.domain.application.ApplicationResourceAssembler;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.service.application.ApplicationRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/23/15.
 */
@RestController
@ExposesResourceFor(Application.class)
@RequestMapping(value = "/applications", produces = "application/hal+json")
public class ApplicationRestController {

    @Autowired
    private ApplicationRestService applicationRestService;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<ApplicationResource> readApplications(){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();
        List<Application> applications = applicationRestService.findAll();
        List<ApplicationResource> applicationResources =
                new ApplicationResourceAssembler().toResources(applications);
        Resources<ApplicationResource> resources = new Resources<ApplicationResource>(applicationResources, link);
        return resources;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createApplication(
        @ModelAttribute("projectAppForm") CreateApplicationCommand createApplicationCommand){

        //applicationRestService.createApplication();

        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setLocation(new URI());

        return new ResponseEntity<Object>(responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{applicationId}", method = RequestMethod.GET)
    public ApplicationResource readApplication(@PathVariable Long applicationId){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();

        Application application = applicationRestService.findOne(applicationId);

        ApplicationResource resource = new ApplicationResource(application, link);
        return resource;
    }

    @RequestMapping(value="/{applicationId}/questionnaires", method = RequestMethod.GET)
    public ApplicationResource readQuestionnaire(@PathVariable Long applicationId){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();
        Application application = applicationRestService.findOne(applicationId);
        ApplicationResource resource = new ApplicationResource(application, link);
        return resource;
    }

    /*
    @RequestMapping(value="/{applicationId}/questionnaires/{questionnaireId}", method = RequestMethod.GET)
    public ApplicationResource readQuestionnaire(@PathVariable Long applicationId, @PathVariable Long questionnaireId){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();
        QuestionnaireEntity questionnaire = questionnaireRestService.findOne(questionnaireId);
        ApplicationResource resource = new ApplicationResource(questionnaire, link);
        return resource;
    }
    */

}
