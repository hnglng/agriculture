package com.sannong.project.presentation.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bright Huang on 1/29/15.
 */
@Controller
@EnableAutoConfiguration
public class HomeController {

    private static final String PROJECT_LANDING_PAGE = "landing";
    private static final String FAQ_PAGE = "faq";

    @RequestMapping(value = {"/home"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView show() {
        return new ModelAndView(PROJECT_LANDING_PAGE);
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public ModelAndView faq() {
        return new ModelAndView(FAQ_PAGE);
    }

    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public ModelAndView showLandingPage() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("landing", new Object());
        return new ModelAndView(PROJECT_LANDING_PAGE, models);
    }

}
