package com.sannong.project.service.application;

import com.sannong.project.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import com.sannong.project.presentation.config.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
/**
 * Created by Bright Huang on 2/2/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationServiceIntegrationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    ApplicationRepository applicationRepository;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {
        /*
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(xpath("//tbody/tr").nodeCount(4));
                */
    }

    @Test
    public void createApplicationCorrectly() {
        CreateApplicationCommand createApplicationCommand = new CreateApplicationCommand();
        /*
        createApplicationCommand.setUser();
        createApplicationCommand.setAnswers();
        createApplicationCommand.setSms();

        applicationService.createApplication(createApplicationCommand);

        applicationRepository.findOne()
        */
    }

}
