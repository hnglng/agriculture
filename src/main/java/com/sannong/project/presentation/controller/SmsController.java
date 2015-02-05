package com.sannong.project.presentation.controller;

import com.sannong.project.domain.common.Error;
import com.sannong.project.domain.common.Status;
import com.sannong.project.domain.user.User;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import com.sannong.project.presentation.dto.Response;
import com.sannong.project.service.sms.SmsService;
import com.sannong.project.service.user.UserRestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Controller
@RequestMapping(value="/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value = "/captcha",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> sendCaptchaCode(@RequestParam String mobilePhone){
        if (StringUtils.isBlank(mobilePhone)) {
            Error error = new Error(Status.CELLPHONE_IS_NULL.getCode(), Status.CELLPHONE_IS_NULL.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }

        String captcha = PasswordGenerator.generateValidationCode(4);
        String result = smsService.sendValidationCode(mobilePhone, captcha);
        if (StringUtils.isNotBlank(result)){
            return new ResponseEntity<Response>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Error>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> sendNewPassword(@RequestParam String mobilePhone, @RequestParam String realName) {
        User user = userRestService.findByMobilePhoneAndRealName(mobilePhone, realName);
        if (user == null) {
            Error error = new Error(
                    Status.NAME_OR_CELLPHONE_NOT_FOUND.getCode(),
                    Status.NAME_OR_CELLPHONE_NOT_FOUND.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }else{
            String password = PasswordGenerator.generatePassword(6);
            String smsResponse = smsService.sendNewPasswordMessage(mobilePhone, password);
            if (StringUtils.isNotBlank(smsResponse)) {
                saveNewPassword(user, password);
                return new ResponseEntity<Response>(HttpStatus.OK);
            } else {
                Error error = new Error(Status.PASSWORD_UNSENT.getCode(), Status.PASSWORD_UNSENT.getMessage());
                return new ResponseEntity<Error>(error, HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }

    private void saveNewPassword(User user, String password){
        user.setPassword(PasswordGenerator.encryptPassword(password, user.getUserName()));
        user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        userRestService.save(user);
    }
}
