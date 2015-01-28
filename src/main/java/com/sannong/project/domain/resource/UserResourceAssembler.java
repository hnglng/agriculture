package com.sannong.project.domain.resource;

import com.sannong.project.domain.user.UserEntity;
import com.sannong.project.presentation.controller.UserRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserResource> {
    public UserResourceAssembler() {
        super(UserRestController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(UserEntity userEntity) {
        UserResource resource = createResourceWithId(userEntity.getUserId(), userEntity);
        return resource;
    }

    @Override
    protected UserResource instantiateResource(UserEntity userEntity) {
        return new UserResource(userEntity);
    }

}
