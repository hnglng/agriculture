package com.sannong.domain.applications;

import jodd.util.sort.TimSort;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author william zhang
 * create questionnaire class
 */
public class Questionnaire implements Serializable {

	private static final long serialVersionUID = 4596287948226478700L;

	private Long applicationId;
	private Integer questionnaireNumber;
	private List<String> answers;
	private Boolean questionnaireCommitted;
	private Timestamp creationTime;
	private Timestamp lastUpdated;
	private String concatenatedAnswers = "";
	private List<Question> questions;

	public Questionnaire() {
	}

	public Questionnaire(Long applicationId, Integer questionnaireNumber) {
		this.applicationId = applicationId;
		this.questionnaireNumber = questionnaireNumber;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getQuestionnaireNumber() {
		return questionnaireNumber;
	}

	public void setQuestionnaireNumber(Integer questionnaireNumber) {
		this.questionnaireNumber = questionnaireNumber;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Boolean getQuestionnaireCommitted() {
		return questionnaireCommitted;
	}

	public void setQuestionnaireCommitted(Boolean questionnaireCommitted) {
		this.questionnaireCommitted = questionnaireCommitted;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getConcatenatedAnswers() {

		if (answers == null){
			return concatenatedAnswers;
		}

		for (String answer : answers){
			concatenatedAnswers = concatenatedAnswers + answer + ",";
		}
		return concatenatedAnswers.substring(0, concatenatedAnswers.lastIndexOf(","));
	}

	public void setConcatenatedAnswers(String concatenatedAnswers) {
		this.concatenatedAnswers = concatenatedAnswers;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
