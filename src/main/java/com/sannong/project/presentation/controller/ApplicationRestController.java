package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.resource.ApplicationResource;
import com.sannong.project.domain.resource.ApplicationResourceAssembler;
import com.sannong.project.service.application.ApplicationRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
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

    //@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"}, method = RequestMethod.GET)
    @RequestMapping(method = RequestMethod.GET)
    public Resources<ApplicationResource> readApplications(){
        String username = "13111111111";

        Link link = linkTo(ApplicationRestController.class).withSelfRel();

        List<ApplicationResource> applicationResources =
                new ApplicationResourceAssembler().toResources(applicationRestService.findByUserUserName(username));

        Resources<ApplicationResource> resources = new Resources<ApplicationResource>(applicationResources, link);
        return resources;

    }

    @RequestMapping(value="/{applicationId}", method = RequestMethod.GET)
    public ApplicationResource readApplication(@PathVariable Long applicationId){
        Link link = linkTo(ApplicationRestController.class).withSelfRel();

        ApplicationResource applicationResource =
                new ApplicationResourceAssembler().toResource(applicationRestService.findOne(applicationId));

        //Resources<ApplicationResource> resources = new Resources<ApplicationResource>(applicationResource, link);
        return applicationResource;

    }

}
