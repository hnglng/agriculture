package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.presentation.controller.ApplicationRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class ApplicationResourceAssembler extends ResourceAssemblerSupport<ApplicationEntity, ApplicationResource> {

    public ApplicationResourceAssembler() {
        super(ApplicationRestController.class, ApplicationResource.class);
    }

    @Override
    public ApplicationResource toResource(ApplicationEntity applicationEntity) {
        ApplicationResource resource = createResourceWithId(applicationEntity.getApplicationId(), applicationEntity);
        return resource;
    }

    @Override
    protected ApplicationResource instantiateResource(ApplicationEntity applicationEntity) {
        return new ApplicationResource(applicationEntity);
    }


}
