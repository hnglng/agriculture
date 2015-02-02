package com.sannong.project.domain.application;

import com.sannong.project.domain.common.Error;
import com.sannong.project.domain.common.ISpecification;
import com.sannong.project.domain.common.Status;
import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.user.User;
import com.sannong.project.infrastructure.persistence.jpa.repositories.SmsRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.UserRepository;
import com.sannong.project.presentation.command.CreateApplicationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bright Huang on 1/7/15.
 */
@Component
public class ApplicationSpecification implements ISpecification<CreateApplicationCommand> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsRepository smsRepository;
    private List<Error> errors = new ArrayList<Error>();

    private boolean isMobilePhoneExisted(String mobilePhone) {
        User user = userRepository.findByMobilePhone(mobilePhone);
        return user != null;
    }

    private boolean isValidCaptcha(String mobilePhone, String smsValidationCode){
        SMS sms = smsRepository.findByMobilePhoneAndSmsValidationCode(mobilePhone, smsValidationCode);
        return sms != null;
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

        boolean isMobilePhoneExisted = isMobilePhoneExisted(mobilePhone);
        if (isMobilePhoneExisted){
            addError(Status.CELLPHONE_EXISTED.getCode(), Status.CELLPHONE_EXISTED.getMessage(), "");
        }

        boolean isValidCaptcha = isValidCaptcha(mobilePhone, smsValidationCode);
        if (!isValidCaptcha){
            addError(Status.CAPTCHA_INCORRECT.getCode(), Status.CAPTCHA_INCORRECT.getMessage(), "");
        }

        return !isMobilePhoneExisted && isValidCaptcha;
    }

    @Override
    public List<Error> getErrors() {
        return errors;
    }


}
