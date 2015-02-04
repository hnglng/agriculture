package com.sannong.project.domain.user;

import com.sannong.project.presentation.controller.api.UserRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserRestController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResourceWithId(user.getUserId(), user);
        return resource;
    }

    @Override
    protected UserResource instantiateResource(User user) {
        return new UserResource(user);
    }

}
