package com.sannong.project.domain.application;

import com.sannong.project.domain.application.Application;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/23/15.
 */

public class ApplicationResource extends Resource {

    private Application application;

    public ApplicationResource(Application application) {
        super(application);
    }


    public ApplicationResource(Application application, Link... links) {
        super(application, links);
        this.application = application;
        //add(linkTo(methodOn(ApplicationRestController.class).readApplications()).withRel("items"));
    }

    public Application getApplication() {
        return application;
    }
}
