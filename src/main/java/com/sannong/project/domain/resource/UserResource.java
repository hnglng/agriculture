package com.sannong.project.domain.resource;

import com.sannong.project.domain.user.UserEntity;
import com.sannong.project.presentation.controller.UserRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class UserResource extends Resource {
    private UserEntity userEntity;

    public UserResource(UserEntity userEntity) {
        super(userEntity);
    }
    
    public UserResource(UserEntity userEntity, Link... links) {
        super(userEntity, links);
        this.userEntity = userEntity;
        add(linkTo(methodOn(UserRestController.class).readUsers()).withRel("items"));
    }

    public UserEntity getUser() {
        return userEntity;
    }
}
