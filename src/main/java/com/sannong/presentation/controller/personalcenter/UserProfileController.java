package com.sannong.presentation.controller.personalcenter;

import com.sannong.domain.region.City;
import com.sannong.domain.region.District;
import com.sannong.domain.share.ResponseStatus;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.user.User;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.presentation.model.Response;
import com.sannong.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 1/22/15.
 */
@Controller
@RequestMapping(value = "/user-profile")
public class UserProfileController {
    private static final Logger logger = Logger.getLogger(UserProfileController.class);

    @Resource
    private IUserService userService;
    @Resource
    private ISmsService smsService;
    @Autowired
    private IValidationService validationService;
    @Autowired
    private IRegionService regionService;


    private String getUserName(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Response getProfile(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        if (StringUtils.isBlank(userName)){
            userName = getUserName();
        }

        Response response = new Response();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        List<User> users = userService.getUserByCondition(map);
        if (!users.isEmpty()){
            User user = users.get(0);
            List<City> cities = regionService.getCities(user.getCompanyProvince());
            List<District> districts = regionService.getDistricts(user.getCompanyCity());
            Map<String, Object> models = new HashMap<String, Object>();
            models.put("userProfile", users.get(0));
            models.put("cities", cities);
            models.put("districts", districts);

            response.setStatusCode(ResponseStatus.OK.getCode());
            response.setStatusMessage(ResponseStatus.OK.getMessage());
            response.setData(models);
        }else{
            response.setStatusCode(ResponseStatus.USER_NOT_FOUND.getCode());
            response.setStatusMessage(ResponseStatus.USER_NOT_FOUND.getMessage());
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateProfile(HttpServletRequest request,
                                                               @ModelAttribute("userProfile") User user) {
        String newCellphone = request.getParameter("newCellphone");
        String validationCode = request.getParameter("validationCode");

        Map<String, Object> models = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(newCellphone) && StringUtils.isNotBlank(validationCode)){
            List<SMS> smsList = smsService.getSmsByCellphoneAndValidationCode(newCellphone, validationCode);
            if (smsList.isEmpty()){
                models.put("status", "error");
                models.put("userProfile", user);
                return models;
            }else{
                user.setMobilePhone(newCellphone);
            }
        }

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        user.setLastUpdated(ts);
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            models.put("status", "error");
        }
        models.put("userProfile", user);
        models.put("status", "saved");
        return models;
    }

    @RequestMapping(value = "/validateUniqueCellphone",method = RequestMethod.GET)
    public @ResponseBody boolean validateUniqueCellphone(HttpServletRequest request){
        String cellphone = request.getParameter("cellphone");
        return validationService.validateUniqueCellphone(cellphone);
    }

    @RequestMapping(value = "/sendValidationCode", method = RequestMethod.POST)
    public @ResponseBody String sendValidationCode(HttpServletRequest request) throws Exception {
        String cellphone = request.getParameter("cellphone");
        String newCellphone = request.getParameter("newCellphone");
        String validationCode = PasswordGenerator.generateValidationCode(4);

        if (StringUtils.isNotBlank(newCellphone)){
            return smsService.sendValidationCode(newCellphone, validationCode);
        } else{
            return smsService.sendValidationCode(cellphone, validationCode);
        }
    }


    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    Response updatePassword(HttpServletRequest request) throws Exception {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmedPassword = request.getParameter("confirmedPassword");
        String userName = request.getParameter("userName");

        if (StringUtils.isBlank(userName)){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
            } else {
                userName = principal.toString();
            }
        }

        Response response = new Response();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", userName);
        List<User> users = userService.getUserByCondition(map);
        if ( ! (users.isEmpty()) ){
            User user = users.get(0);
            String encryptOldPassword = PasswordGenerator.encryptPassword(oldPassword, userName);
            if ( ! (user.getPassword().equals(encryptOldPassword))){
                response.setStatusCode(ResponseStatus.OLD_PASSWORD_MISMATCH.getCode());
                response.setStatusMessage(ResponseStatus.OLD_PASSWORD_MISMATCH.getMessage());
            }else if ( ! (newPassword.equals(confirmedPassword)) ){
                response.setStatusCode(ResponseStatus.CONFIRMED_PASSWORD_MISMATCH.getCode());
                response.setStatusMessage(ResponseStatus.CONFIRMED_PASSWORD_MISMATCH.getMessage());
            }
            String encryptedNewPassword = PasswordGenerator.encryptPassword(newPassword, userName);
            user.setPassword(encryptedNewPassword);
            userService.updatePassword(user);
            response.setStatusCode(ResponseStatus.PASSWORD_UPDATED.getCode());
            response.setStatusMessage(ResponseStatus.PASSWORD_UPDATED.getMessage());
        }else{
            response.setStatusCode(ResponseStatus.USER_NOT_FOUND.getCode());
            response.setStatusMessage(ResponseStatus.USER_NOT_FOUND.getMessage());
        }

        return response;
    }

}
