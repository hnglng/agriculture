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
	private String answers;
	private Boolean committed;
	private Timestamp creationTime;
	private Timestamp last_updated;

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

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Boolean getCommitted() {
		return committed;
	}

	public void setCommitted(Boolean committed) {
		this.committed = committed;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Timestamp last_updated) {
		this.last_updated = last_updated;
	}
}
