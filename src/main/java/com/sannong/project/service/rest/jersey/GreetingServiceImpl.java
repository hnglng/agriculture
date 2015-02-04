package com.sannong.project.service.rest.jersey;

import org.springframework.stereotype.Component;

/**
 * Created by Bright Huang on 1/2/15.
 */
@Component
public class GreetingServiceImpl implements GreetingService{
    @Override
    public String greet(String who) {
        return String.format("hello, %s!", who);
    }
}
