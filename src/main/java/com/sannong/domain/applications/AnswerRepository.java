package com.sannong.domain.applications;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.applications.Answer;

/**
 * answer reporitory
 * @author william zhang
 */
@Repository
@Transactional
public interface AnswerRepository {
	
	Answer getAnswerByUserName(String userName);
	
	void addAnswers(Answer answer);
	
	void updateAnswers(Answer answer);
}
