package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.sms.SMS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("smsRepository")
public interface SmsRepository  extends CrudRepository<SMS, Long> {

    SMS findByMobilePhoneAndSmsValidationCode(String mobilePhone, String smsValidationCode);

	/*
	List<SMS> getNewSMS();
	
	void addNewSMS(SMS sms);
	
	void updateSMS(SMS sms);
	
    void updateSmsByCellphone(SMS sms);
    
    List<SMS> getSmsByCellphoneAndValidationCode(SMS sms);
    */
}
