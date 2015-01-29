package com.sannong.project.presentation.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user-personal-center")
public class PersonalCenterController {
    private static final String USER_PERSONAL_CENTER_PAGE = "user-personal-center";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showUserPersonalCenter() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("user-personal-center", new Object());
        return new ModelAndView(USER_PERSONAL_CENTER_PAGE, models);
    }
}
