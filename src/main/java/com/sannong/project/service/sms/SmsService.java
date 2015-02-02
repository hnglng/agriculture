package com.sannong.project.service.sms;


import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.sms.SmsUrlFactory;
import com.sannong.project.infrastructure.persistence.jpa.repositories.SmsRepository;
import com.sannong.project.infrastructure.sms.SmsSender;
import com.sannong.project.infrastructure.util.AppConfigReader;
import com.sannong.project.infrastructure.util.PasswordGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Bright Huang on 10/22/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsService {

    private static final Logger logger = Logger.getLogger(SmsService.class);

    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private AppConfigReader appConfigReader;
    @Autowired
    private SmsUrlFactory smsUrlFactory;

    public boolean updateSMS(HttpServletRequest request) {
        SMS sms = new SMS();
        String id = request.getParameter("smsid");
        //String time = request.getParameter("sendtime");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (id.length() < 0) {
            return false;
        }
        long smsId = new Integer(id);

        sms.setSmsId(smsId);
        sms.setSentTime(time);
        sms.setSmsStatus(1);
        smsRepository.save(sms);
        return true;
    }

    public int validateSMSCode(HttpServletRequest request) {
        String smsCode = request.getParameter("validationCode");

        if (StringUtils.isBlank(smsCode)) {
            return 0;
        }

        if (request.getSession().getAttribute(appConfigReader.getSessionSmsCodes()) == null) {
            return 1;
        }

        Map<Date, String> map = (HashMap<Date, String>) request.getSession().getAttribute(appConfigReader.getSessionSmsCodes());

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            Date dt = (Date) mapEntry.getKey();
            String savecode = mapEntry.getValue().toString();
            if (savecode.equals(smsCode)) {
                Date dtNow = new Date(System.currentTimeMillis());
                Date dtSms = new Date(dt.getTime());

                @SuppressWarnings("deprecation")
                int diffInHours = dtNow.getHours() - dt.getHours();

                @SuppressWarnings("deprecation")
                int diffInMinuts = dtNow.getMinutes() - dt.getMinutes();
                if (diffInHours == 0 && diffInMinuts < 5) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    public SMS getSmsByMobilePhoneAndValidationCode(String mobilePhone, String validationCode) {
        return smsRepository.findByMobilePhoneAndSmsValidationCode(mobilePhone, validationCode);
    }

    public boolean generateCode(HttpServletRequest request) {

        String mobile = request.getParameter("mobile").toString();
        String smstype = request.getParameter("smstype").toString();


        String regcode = PasswordGenerator.generateValidationCode(4);

        Map<Date, String> map = new HashMap<Date, String>();
        if (mobile.length() < 11)
            return false;
        else {
            SMS sms = new SMS();

            sms.setMobilePhone(mobile);
            sms.setSmsValidationCode(regcode);

            Date ts = new Date(System.currentTimeMillis());
            if (request.getSession().getAttribute(appConfigReader.getSessionSmsCodes()) != null) {
                map = (HashMap<Date, String>) request.getSession().getAttribute(appConfigReader.getSessionSmsCodes());
            }
            map.put(ts, regcode);

            request.getSession().setAttribute(appConfigReader.getSessionSmsCodes(), map);

            String content = "验证码为:" + regcode;
            if (smstype.equals("0")) {
                content = appConfigReader.getProperty("sms-welcome").replace("{0}", regcode);
            }
            if (smstype.equals("1")) {
                content = appConfigReader.getProperty("sms-changeMobile").replace("{0}", regcode);
                //content="改变你在三农网上的注册手机号码，验证码为:"+regcode;
            }
            if (smstype.equals("2")) {
                content = appConfigReader.getProperty("sms-newPassword").replace("{0}", regcode);
                //content="你在三农网上新密码为:"+regcode;
            }

            sms.setSmsContent(content);
            sms.setSentTime(new Timestamp(System.currentTimeMillis()));
            smsRepository.save(sms);

            return true;
        }
    }



    public String sendValidationCode(String cellphone, String validationCode) {
        String result = "";
        try {
            String smsUrl = smsUrlFactory.generateValidationCodeSmsUrl(cellphone, validationCode);
            SmsSender smsSender = new SmsSender();
            result = smsSender.sendSms(smsUrl);
            addSmsRecord(cellphone, validationCode, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }


    public String sendLoginMessage(String cellphone, String password) {
        String result = "";
        try {
            String smsUrl = smsUrlFactory.generateLoginMessageSmsUrl(cellphone, password);
            SmsSender smsSender = new SmsSender();
            result = smsSender.sendSms(smsUrl);
            addSmsRecord(cellphone, password, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }


    public String sendNewPasswordMessage(String cellphone, String password) {
        String result = "";
        try {
            String url = smsUrlFactory.generateNewPasswordSmsUrl(cellphone, password);
            SmsSender smsSender = new SmsSender();
            result = smsSender.sendSms(url);
            addSmsRecord(cellphone, password, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    private void addSmsRecord(String cellphone, String password, String result) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isNotBlank(result)) {
            SMS sms = new SMS();
            sms.setMobilePhone(cellphone);
            sms.setSmsValidationCode(password);
            sms.setSmsContent(result);
            sms.setSentTime(timestamp);
            sms.setSmsStatus(0);
            smsRepository.save(sms);
        }
    }

}
