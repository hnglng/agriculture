package com.sannong.project.domain.sms;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

;

@Repository
@Transactional
public interface SmsRepository {
	
	List<SMS> getNewSMS();
	
	void addNewSMS(SMS sms);
	
	void updateSMS(SMS sms);
	
    void updateSmsByCellphone(SMS sms);
    
    List<SMS> getSmsByCellphoneAndValidationCode(SMS sms);
}
