package com.sannong.project.presentation.controller;

import com.sannong.project.domain.common.Status;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import com.sannong.project.presentation.dto.Response;
import com.sannong.project.service.sms.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Controller
@RequestMapping(value="/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/captcha",method = RequestMethod.POST)
    public @ResponseBody
    Response sendCaptchaCode(@RequestParam String mobilePhone){
        Response response = new Response();

        if (StringUtils.isBlank(mobilePhone)) {
            response.setStatusCode(Status.CELLPHONE_IS_NULL.getCode());
            response.setStatusMessage(Status.CELLPHONE_IS_NULL.getMessage());
            return response;
        }

        String captcha = PasswordGenerator.generateValidationCode(4);
        String result = smsService.sendValidationCode(mobilePhone, captcha);
        if (StringUtils.isNotBlank(result)){
            response.setStatusCode(Status.CAPTCHA_SENT.getCode());
            response.setStatusMessage(Status.CAPTCHA_SENT.getMessage());
        }else{
            response.setStatusCode(Status.FAILURE.getCode());
            response.setStatusMessage(Status.FAILURE.getMessage());
        }
        return response;
    }
}
