package com.sannong.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by Bright Huang on 1/1/15.
 */
public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication () {
        register(RequestContextFilter.class);
        register(HelloWorld.class);

        //register(ProjectApplicationController.class);

        /*
        register(JerseyResource.class);
        register(SpringSingletonResource.class);
        register(SpringRequestResource.class);
        register(CustomExceptionMapper.class);
        */
    }
}
