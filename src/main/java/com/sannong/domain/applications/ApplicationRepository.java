package com.sannong.domain.applications;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.applications.Application;

/**
 * answer reporitory
 * @author william zhang
 */
@Repository
@Transactional
public interface ApplicationRepository {

	Application getApplicationBy(String userName);

	Questionnaire getQuestionnaireBy(Long applicationId, Integer questionnaireNumber);

	void addProjectApplicationInfo(Application application);
	
}
