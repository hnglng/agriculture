package com.sannong.presentation.controller;

import com.sannong.domain.resource.ApplicationResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bright Huang on 1/23/15.
 */
@RestController
@ExposesResourceFor(ApplicationResource.class)
@RequestMapping("/application")
public class ApplicationRestController {
}
