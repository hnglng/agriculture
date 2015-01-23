package com.sannong.domain.project;

import com.sannong.domain.common.*;
import com.sannong.domain.common.Error;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.sms.SmsRepository;
import com.sannong.domain.user.User;
import com.sannong.domain.user.UserRepository;
import com.sannong.presentation.command.CreateApplicationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 1/7/15.
 */
@Component
public class ApplicationSpecification implements ISpecification<CreateApplicationCommand>{
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Qualifier("smsRepository")
    @Autowired
    private SmsRepository smsRepository;

    private List<Error> errors = new ArrayList<Error>();


    private boolean isMobilePhoneExisted(String mobilePhone) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobilePhone", mobilePhone);
        List<User> users = userRepository.getUserByCondition(map);
        return !users.isEmpty();
    }

    private boolean isValidCaptcha(String mobilePhone, String smsValidationCode){
        SMS sms = new SMS();
        sms.setMobilePhone(mobilePhone);
        sms.setSmsValidationCode(smsValidationCode);
        List<SMS> smsList = smsRepository.getSmsByCellphoneAndValidationCode(sms);
        return !smsList.isEmpty();
    }

    private void addError(int code, String message, String field){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        error.setField(field);
        errors.add(error);

    }

    @Override
    public boolean isSatisfiedBy(CreateApplicationCommand createApplicationCommand){
        String mobilePhone = createApplicationCommand.getUser().getMobilePhone();
        String smsValidationCode = createApplicationCommand.getSms().getSmsValidationCode();

        boolean isMobilePhoneNotExisted = isMobilePhoneExisted(mobilePhone);
        if (isMobilePhoneNotExisted){
            addError(Status.CELLPHONE_EXISTED.getCode(), Status.CELLPHONE_EXISTED.getMessage(), "");
        }

        boolean isValidCaptcha = isValidCaptcha(mobilePhone, smsValidationCode);
        if (isValidCaptcha){
            addError(Status.CAPTCHA_INCORRECT.getCode(), Status.CAPTCHA_INCORRECT.getMessage(), "");
        }

        return isMobilePhoneNotExisted && isValidCaptcha;
    }

    @Override
    public List<Error> getErrors() {
        return errors;
    }


}
