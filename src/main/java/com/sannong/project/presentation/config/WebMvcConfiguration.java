package com.sannong.project.presentation.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Configuration
@ComponentScan(basePackages = {"com.sannong.project"})
@EnableWebMvc
@EnableAutoConfiguration
@EnableSpringDataWebSupport
@EnableHypermediaSupport(type= {EnableHypermediaSupport.HypermediaType.HAL})
class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer c) {
        c.defaultContentType(MediaTypes.HAL_JSON);
    }

}
