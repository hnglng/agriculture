package com.sannong.project.presentation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * Created by Bright Huang on 1/30/15.
 */


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.sannong.project")
@EntityScan("com.sannong.project.domain")
@EnableJpaRepositories("com.sannong.project.infrastructure.persistence.jpa.**.repositories")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
//@SpringBootApplication
//@ImportResource({"classpath*:application-context.xml"})
//@EnableConfigurationProperties
//@ConfigurationProperties(locations = "classpath:application.properties")
public class Application extends SpringBootServletInitializer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ctx.getDisplayName();
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
