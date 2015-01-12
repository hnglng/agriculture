package com.sannong.domain.applications;

import com.sannong.domain.share.ISpecification;
import com.sannong.domain.share.ResponseStatus;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.sms.SmsRepository;
import com.sannong.domain.user.User;
import com.sannong.domain.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 1/7/15.
 */
@Component
public class ApplicationSpecification implements ISpecification<Application>{
    Map<Integer, String> unsatisfiedReasons = new HashMap<Integer, String>();

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Qualifier("smsRepository")
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public boolean isSatisfiedBy(Application application){
        String mobilePhone = application.getUser().getMobilePhone();
        String smsValidationCode = application.getSms().getSmsValidationCode();

        boolean isMobilePhoneNotNull = isMobilePhoneNotNull(mobilePhone);
        boolean isMobilePhoneNotExisted = isMobilePhoneNotRegistered(mobilePhone);
        boolean isValidationCodeValid = isValidationCodeValid(mobilePhone, smsValidationCode);

        return isMobilePhoneNotNull && isMobilePhoneNotExisted && isValidationCodeValid;
    }

    public boolean isSatisfiedBy(String mobilePhone){
        boolean isMobilePhoneNotNull = isMobilePhoneNotNull(mobilePhone);
        boolean isMobilePhoneNotExisted = isMobilePhoneNotRegistered(mobilePhone);
        return isMobilePhoneNotNull && isMobilePhoneNotExisted;
    }


    private boolean isMobilePhoneNotNull(String mobilePhone){
        if (StringUtils.isNotBlank(mobilePhone)){
            return true;
        }else{
            unsatisfiedReasons.put(
                    ResponseStatus.CELLPHONE_IS_NULL.getCode(),
                    ResponseStatus.CELLPHONE_IS_NULL.getMessage());
            return false;
        }
    }

    private boolean isMobilePhoneNotRegistered(String mobilePhone) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobilePhone", mobilePhone);
        List<User> users = userRepository.getUserByCondition(map);
        if (users.isEmpty()) {
            return true;
        } else {
            unsatisfiedReasons.put(
                    ResponseStatus.CELLPHONE_EXISTED.getCode(),
                    ResponseStatus.CELLPHONE_EXISTED.getMessage());
            return false;
        }
    }

    private boolean isValidationCodeValid(String mobilePhone, String smsValidationCode){
        SMS sms = new SMS();
        sms.setMobilePhone(mobilePhone);
        sms.setSmsValidationCode(smsValidationCode);
        List<SMS> smsList = smsRepository.getSmsByCellphoneAndValidationCode(sms);
        if (smsList.isEmpty()) {
            unsatisfiedReasons.put(
                    ResponseStatus.CAPTCHA_INCORRECT.getCode(),
                    ResponseStatus.CAPTCHA_INCORRECT.getMessage());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<Integer, String> getUnsatisfiedReasons() {
        return unsatisfiedReasons;
    }

}
