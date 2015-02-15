package com.sannong.project.presentation.controller;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sannong.project.domain.user.User;
import com.sannong.project.domain.common.Status;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import com.sannong.project.presentation.dto.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bright Huang on 10/14/14.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
    private static final String LOGIN_PAGE = "login";
    private static final String USER_MANAGEMENT_PAGE = "user-management";

//    @Resource
//    private IUserService userService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView showLoginPage() {
        return new ModelAndView(LOGIN_PAGE);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login() {
        return showLoginPage();
    }


    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public @ResponseBody
    Response handleLoginSuccessOnPost() {
        return handleLoginSuccess();
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public @ResponseBody
    Response handleLoginSuccess() {
        Response response = new Response();
        response.setStatusCode(Status.LOGIN_SUCCESS.getCode());
        response.setStatusMessage(Status.LOGIN_SUCCESS.getMessage());
        response.setURI(USER_MANAGEMENT_PAGE);
        return response;
    }

    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public @ResponseBody
    Response handleLoginFailureOnPost() {
        return handleLoginFailure();
    }

    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    public @ResponseBody
    Response handleLoginFailure() {
        return new Response(
                Status.USERNAME_OR_PASSWORD_ERROR.getCode(),
                Status.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView handleAccessDenied() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("access-denied", "access-denied");
        return new ModelAndView(LOGIN_PAGE, models);
    }

    /*
    @RequestMapping(value = "login/realName", method = RequestMethod.POST)
    public @ResponseBody Response getRealName(){
        Map<String, Object> models = new HashMap<String, Object>();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        Response response = new Response();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            String userName = userDetails.getUsername();
            String realName = "";
            queryParamMap.put("userName", userName);
            List<User> users = userService.getUserByCondition(queryParamMap);
            if (!users.isEmpty()){
                User user = users.get(0);
                realName = user.getRealName();
            }

            models.put("realName", realName);
            response.setStatusCode(Status.OK.getCode());
            response.setStatusMessage(Status.OK.getMessage());
            response.setData(models);
        }catch(Exception ex){
            logger.error(ex.getMessage());
            response.setStatusCode(Status.FAILURE.getCode());
            response.setStatusMessage(Status.FAILURE.getMessage());
        }
        return response;
    }
    */
}


