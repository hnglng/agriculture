package com.sannong.project.domain.application;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * answer reporitory
 * @author william zhang
 */
@Repository
@Transactional
public interface ApplicationRepository {

	List<Application> findByUserName(String userName);

	Questionnaire getQuestionnaireBy(Long applicationId, Integer questionnaireNumber);

	int getTotalQuestions();

	List<Question> findQuestionsByQuestionnaireNumber(int questionnaireNumber);

	void addQuestionnaire(Questionnaire questionnaire);

	void addProjectApplicationInfo(Application application);
	
}