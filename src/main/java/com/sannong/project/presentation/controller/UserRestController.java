package com.sannong.project.presentation.controller;

import com.sannong.project.domain.resource.UserResource;
import com.sannong.project.domain.resource.UserResourceAssembler;
import com.sannong.project.domain.user.UserEntity;
import com.sannong.project.service.user.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(UserEntity.class)
@RequestMapping(value = "/users", produces = "application/hal+json")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<UserResource> readUsers(){
        Link link = linkTo(UserRestController.class).withSelfRel();
        List<UserEntity> users = userRestService.findAll();
        List<UserResource> userResources =
                new UserResourceAssembler().toResources(users);
        Resources<UserResource> resources = new Resources<UserResource>(userResources, link);
        return resources;
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public UserResource readUser(@PathVariable Long userId){
        Link link = linkTo(UserRestController.class).withSelfRel();
        UserEntity user = userRestService.findOne(userId);
        UserResource resource = new UserResource(user, link);
        return resource;
    }

}
