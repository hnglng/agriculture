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
import com.sannong.project.service.sms.ISmsService;
import com.sannong.project.service.user.IUserService;
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
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
    private static final String LOGIN_PAGE = "login";
    private static final String FAQ_PAGE = "faq";
    private static final String USER_PERSONAL_CENTER_PAGE = "user-personal-center";
    private static final String PROJECT_LANDING_PAGE = "landing";

    @Resource
    private IUserService userService;
    @Resource
    private ISmsService smsService;


    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView(PROJECT_LANDING_PAGE);
    }

    @RequestMapping(value = "faq", method = RequestMethod.GET)
    public ModelAndView faq() {
        return new ModelAndView(FAQ_PAGE);
    }

    @RequestMapping(value = "landing", method = RequestMethod.GET)
    public ModelAndView showLandingPage() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("landing", new Object());
        return new ModelAndView(PROJECT_LANDING_PAGE, models);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView(LOGIN_PAGE);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login() {
        return showLoginPage();
    }

    @RequestMapping(value = "access-denied", method = RequestMethod.GET)
    public ModelAndView handleAccessDenied() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("access-denied", "access-denied");
        return new ModelAndView(LOGIN_PAGE, models);
    }

    @RequestMapping(value = "login-success", method = RequestMethod.POST)
    public @ResponseBody
    Response handleLoginSuccessOnPost() {
        return handleLoginSuccess();
    }

    @RequestMapping(value = "login-success", method = RequestMethod.GET)
    public @ResponseBody
    Response handleLoginSuccess() {
        Response response = new Response();
        response.setStatusCode(Status.LOGIN_SUCCESS.getCode());
        response.setStatusMessage(Status.LOGIN_SUCCESS.getMessage());
        response.setURI(USER_PERSONAL_CENTER_PAGE);
        return response;
    }

    @RequestMapping(value = "login-failure", method = RequestMethod.POST)
    public @ResponseBody
    Response handleLoginFailureOnPost() {
        return handleLoginFailure();
    }

    @RequestMapping(value = "login-failure", method = RequestMethod.GET)
    public @ResponseBody
    Response handleLoginFailure() {
        return new Response(
                Status.USERNAME_OR_PASSWORD_ERROR.getCode(),
                Status.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }

    /**
     * From forgot-password page, user try to get a new password to login.
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "forgot-password/sendNewPasswordMessage", method = RequestMethod.POST)
    public @ResponseBody
    Response sendNewPasswordMessage(HttpServletRequest request) throws Exception{
        String cellphone = request.getParameter("cellphone");
        String realName = request.getParameter("realName");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cellphone", cellphone);
        paramMap.put("realName", realName);

        Response response = new Response();
        List<User> users = userService.getUserByCondition(paramMap);
        if (users.isEmpty()) {
            response.setStatusCode(Status.NAME_OR_CELLPHONE_NOT_FOUND.getCode());
            response.setStatusMessage(Status.NAME_OR_CELLPHONE_NOT_FOUND.getMessage());
        } else {
            User user = users.get(0);
            if (!(user.getMobilePhone().equals(cellphone) && user.getRealName().equals(realName))) {
                response.setStatusCode(Status.NAME_OR_CELLPHONE_MISMATCH.getCode());
                response.setStatusMessage(Status.NAME_OR_CELLPHONE_MISMATCH.getMessage());
            }
            String password = PasswordGenerator.generatePassword(6);
            String smsResponse = smsService.sendNewPasswordMessage(cellphone, password);
            if (StringUtils.isNotBlank(smsResponse)){
                user.setPassword(PasswordGenerator.encryptPassword(password, user.getUserName()));
                user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                userService.updatePassword(user);
                response.setStatusCode(Status.PASSWORD_SENT.getCode());
                response.setStatusMessage(Status.PASSWORD_SENT.getMessage());
            } else {
                response.setStatusCode(Status.PASSWORD_UNSENT.getCode());
                response.setStatusMessage(Status.PASSWORD_UNSENT.getMessage());
            }
        }
        return response;
    }

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

}


