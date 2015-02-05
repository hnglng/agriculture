package com.sannong.project.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bright Huang on 2/4/15.
 */
public class UserPaginationResource extends Resource{
    public UserPaginationResource(Page<User> users, Link... links) {
        super(users, links);
    }
}
