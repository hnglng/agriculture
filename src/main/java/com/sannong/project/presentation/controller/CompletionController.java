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
@RequestMapping(value = "/completion")
public class CompletionController {
    private static final String COMPLETION_PAGE = "completion";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCompletion() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("completion", new Object());
        return new ModelAndView(COMPLETION_PAGE, models);
    }
}
