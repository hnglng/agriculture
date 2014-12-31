package com.sannong.domain.sms;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.sms.SMS;;

@Repository
@Transactional
public interface SmsRepository {
	
	List<SMS> getNewSMS();
	
	void addNewSMS(SMS sms);
	
	void updateSMS(SMS sms);
	
    void updateSmsByCellphone(SMS sms);
    
    List<SMS> getSmsByCellphoneAndValidationCode(SMS sms);
}
