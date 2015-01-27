package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.presentation.controller.ApplicationRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Bright Huang on 1/23/15.
 */

public class ApplicationResource extends Resource {
    private ApplicationEntity applicationEntity;

    public ApplicationResource(ApplicationEntity applicationEntity) {
        super(applicationEntity);
    }


    public ApplicationResource(ApplicationEntity applicationEntity, Link... links) {
        super(applicationEntity, links);
        this.applicationEntity = applicationEntity;
        add(linkTo(methodOn(ApplicationRestController.class).readApplications()).withRel("items"));
    }

    public ApplicationEntity getApplication() {
        return applicationEntity;
    }
}
