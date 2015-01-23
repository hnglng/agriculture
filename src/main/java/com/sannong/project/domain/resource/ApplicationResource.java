package com.sannong.project.domain.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class ApplicationResource extends Resource {
    public ApplicationResource(Object content, Link... links) {
        super(content, links);
    }

    public ApplicationResource(Object content, Iterable iterable) {
        super(content, iterable);
    }
}
