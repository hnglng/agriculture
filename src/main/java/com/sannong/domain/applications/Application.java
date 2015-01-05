package com.sannong.domain.applications;

import com.sannong.domain.sms.SMS;
import com.sannong.domain.user.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author william zhang
 * create application class
 */
public class Application implements Serializable{

	private static final long serialVersionUID = 4282850771969955235L;
	
	private Long applicationId;
	private User user;
	private List<Questionnaire> questionnaires;
	private String comments;
	private Timestamp creationDate;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
