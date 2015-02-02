package com.sannong.project.domain.user;

import com.sannong.project.domain.user.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class UserResource extends Resource {

    private User user;

    public UserResource(User user) {
        super(user);
    }

    public UserResource(User user, Link... links) {
        super(user, links);
        this.user = user;
        //add(linkTo(methodOn(UserRestController.class).readUser(userEntity.getUserId())).withSelfRel());
    }

    public User getUser() {
        return user;
    }

}
