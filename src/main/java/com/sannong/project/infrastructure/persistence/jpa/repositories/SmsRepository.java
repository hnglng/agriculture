package com.sannong.project.infrastructure.persistence.jpa.repositories;
import java.util.List;

import com.sannong.project.domain.sms.SMS;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface SmsRepository {
	
	List<SMS> getNewSMS();
	
	void addNewSMS(SMS sms);
	
	void updateSMS(SMS sms);
	
    void updateSmsByCellphone(SMS sms);
    
    List<SMS> getSmsByCellphoneAndValidationCode(SMS sms);
}
