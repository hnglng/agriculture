package com.sannong.domain.applications;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.applications.Application;

import java.util.List;

/**
 * answer reporitory
 * @author william zhang
 */
@Repository
@Transactional
public interface ApplicationRepository {

	Application getApplicationBy(String userName);

	Questionnaire getQuestionnaireBy(Long applicationId, Integer questionnaireNumber);

	int getTotalQuestions();

	List<Question> findQuestionsByQuestionnaireNumber(int questionnaireNumber);

	void addQuestionnaire(Questionnaire questionnaire);

	void addProjectApplicationInfo(Application application);
	
}
