package com.sannong.domain.resource;

import com.sannong.domain.application.ApplicationEntity;
import com.sannong.presentation.controller.ApplicationController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class ApplicationResourceAssembler extends ResourceAssemblerSupport<ApplicationEntity, ApplicationResource> {


    public ApplicationResourceAssembler(Class<ApplicationController> controllerClass, Class<ApplicationResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public ApplicationResource toResource(ApplicationEntity applicationEntity) {
        ApplicationResource resource = createResourceWithId(applicationEntity.getApplicationId(), applicationEntity);
        return resource;
    }

    @Override
    protected ApplicationResource instantiateResource(ApplicationEntity entity) {
        return new ApplicationResource(entity);
    }
}
