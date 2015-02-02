package com.sannong.project.presentation.filter;


import com.sannong.project.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Bright Huang on 11/13/14.
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    @Autowired
    UserService userService;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String usernameParamName = getUsernameParameter();
        String usernameParamValue = request.getParameter(usernameParamName);
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(request);
        try{
            //String userName = userService.getUserNameByMobilePhone(usernameParamValue);
            String userName = null;
            if (StringUtils.isNotBlank(userName)){
                httpRequestWrapper.addParameter(usernameParamName, userName);
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }

        Authentication authentication;

        if (httpRequestWrapper.getParamMap().isEmpty()){
            authentication = super.attemptAuthentication(request, response);
        }else{
            authentication = super.attemptAuthentication(httpRequestWrapper, response);
        }

        return authentication;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {

        super.setRememberMeServices(rememberMeServices);
    }

    public RememberMeServices getRememberMeServices() {
        return super.getRememberMeServices();
    }
}
