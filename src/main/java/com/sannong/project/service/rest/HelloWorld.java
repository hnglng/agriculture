package com.sannong.project.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Bright Huang on 1/2/15.
 */
@Path("hello")
@Component
public class HelloWorld {
    @Autowired
    private GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return greetingService.greet("world");
    }
}
