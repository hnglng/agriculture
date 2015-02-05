package com.sannong.project.presentation.controller.api;

import com.sannong.project.domain.user.User;
import com.sannong.project.domain.user.UserPaginationResource;
import com.sannong.project.domain.user.UserResource;
import com.sannong.project.domain.user.UserResourceAssembler;
import com.sannong.project.service.user.UserRestService;
import com.sannong.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/api/users", produces = "application/hal+json")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRestService userRestService;

    /*
    @RequestMapping(method = RequestMethod.GET)
    public Resources<UserResource> readUsers(@RequestParam Integer pageNumber, @RequestParam Integer perPage){
        Link link = linkTo(UserRestController.class).withSelfRel();
        Page<User> users = userService.getUsers(pageNumber, perPage);
        List<UserResource> userResources =
                new UserResourceAssembler().toResources(users);
        Resources<UserResource> resources = new Resources<UserResource>(userResources, link);
        return resources;
    }
    */

    @RequestMapping(method = RequestMethod.GET)
    public UserPaginationResource readUsers(@RequestParam Integer pageNumber, @RequestParam Integer perPage){
        Link link = linkTo(UserRestController.class).withSelfRel();
        Page<User> users = userService.getUsers(pageNumber, perPage);
        UserPaginationResource resource = new UserPaginationResource(users, link);
        return resource;
    }


    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public UserResource readUser(@PathVariable Long userId){
        Link link = linkTo(UserRestController.class).withRel("items");
        User user = userRestService.findOne(userId);
        UserResource resource = new UserResource(user, link);
        return resource;
    }

}
