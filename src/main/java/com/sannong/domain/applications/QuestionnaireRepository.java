package com.sannong.domain.applications;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.applications.Question;

/**
 * @author Bright Huang
 */
@Repository
@Transactional
public interface QuestionnaireRepository {
	
	List<Question> findQuestionsByQuestionnaireNumber(int questionnaireNumber);
	void addQuestionnaire(Questionnaire questionnaire);
}
