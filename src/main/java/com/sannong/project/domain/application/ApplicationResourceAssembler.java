package com.sannong.project.domain.application;

import com.sannong.project.presentation.controller.api.ApplicationRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class ApplicationResourceAssembler extends ResourceAssemblerSupport<Application, ApplicationResource> {


    public ApplicationResourceAssembler() {
        super(ApplicationRestController.class, ApplicationResource.class);
    }

    @Override
    public ApplicationResource toResource(Application application) {
        ApplicationResource resource = createResourceWithId(application.getApplicationId(), application);
        return resource;
    }

    @Override
    protected ApplicationResource instantiateResource(Application application) {
        return new ApplicationResource(application);
    }

}
