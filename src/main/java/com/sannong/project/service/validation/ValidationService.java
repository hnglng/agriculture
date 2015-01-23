package com.sannong.project.service.validation;

import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.user.User;
import com.sannong.project.domain.sms.SmsRepository;
import com.sannong.project.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 11/18/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValidationService implements IValidationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public boolean validateUniqueCellphone(String cellphone) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cellphone", cellphone);
        List<User> users = userRepository.getUserByCondition(map);
        if (users.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateValidationCode(String cellphone, String validationCode) {
        SMS sms = new SMS();
        sms.setMobilePhone(cellphone);
        sms.setSmsValidationCode(validationCode);
        List<SMS> smsList = smsRepository.getSmsByCellphoneAndValidationCode(sms);
        if (smsList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
