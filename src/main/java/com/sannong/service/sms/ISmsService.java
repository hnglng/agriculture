package com.sannong.service.sms;

import com.sannong.domain.sms.SMS;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Bright Huang on 10/22/14.
 */
public interface ISmsService {

    public boolean updateSMS(HttpServletRequest request);
    public List<SMS> getNewSMS();
    public String sendValidationCode(String cellphone, String validationCode);
    public String sendLoginMessage(String cellphone, String password);
    public String sendNewPasswordMessage(String cellphone, String password);
    public List<SMS> getSmsByCellphoneAndValidationCode(String cellphone, String validationCode);
}
