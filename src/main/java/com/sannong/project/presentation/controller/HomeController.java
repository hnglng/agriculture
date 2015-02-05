package com.sannong.project.presentation.controller;

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
public class HomeController {
    private static final String PROJECT_LANDING_PAGE = "landing";
    private static final String FAQ_PAGE = "faq";
    private static final String COMPLETION_PAGE = "completion";

    @RequestMapping(value = {"/home", "/landing"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView show() {
        return new ModelAndView(PROJECT_LANDING_PAGE);
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public ModelAndView faq() {
        return new ModelAndView(FAQ_PAGE);
    }

    @RequestMapping(value = "/completion", method = RequestMethod.GET)
    public ModelAndView showCompletion() {
        return new ModelAndView(COMPLETION_PAGE);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView showError() {
        return new ModelAndView("error");
    }

}
