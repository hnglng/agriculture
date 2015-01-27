package com.sannong.project.domain.application;

import com.sannong.project.domain.common.AbstractEntity;
import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.user.User;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author william zhang
 * create application class
 */

public class Application implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 4282850771969955235L;
	
	private Long applicationId;
	private User user;
	private List<Questionnaire> questionnaires;
	private String comments;
	private Timestamp creationTime;
	private SMS sms;

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

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public SMS getSms() {
		return sms;
	}

	public void setSms(SMS sms) {
		this.sms = sms;
	}
}
