package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.domain.resource.ApplicationResource;
import com.sannong.project.domain.resource.ApplicationResourceAssembler;
import com.sannong.project.service.application.ApplicationRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Long applicationId = new Long(21);

        Link link = linkTo(ApplicationRestController.class).withSelfRel();

        ApplicationEntity application = applicationRestService.findOne(applicationId);

        List<ApplicationEntity> applications = new ArrayList<ApplicationEntity>();
        applications.add(application);

        List<ApplicationResource> applicationResources =
                new ApplicationResourceAssembler().toResources(applications);

        Resources<ApplicationResource> resources = new Resources<ApplicationResource>(applicationResources, link);
        return resources;

    }

    @RequestMapping(value="/{applicationId}", method = RequestMethod.GET)
    public ApplicationResource readApplication(@PathVariable Long applicationId){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();
        ApplicationEntity application = applicationRestService.findOne(applicationId);
        ApplicationResource resource = new ApplicationResource(application, link);
        return resource;
    }

}
