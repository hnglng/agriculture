package com.sannong.domain.sms;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author william zhang
 * create sms class
 */
public class SMS implements Serializable {

	private static final long serialVersionUID = -5119299893247639411L;
	
	private Long smsId;
	private String smsValidationCode;
	private Timestamp sentTime;
	private String mobilePhone;
	private String smsContent;
	private int smsStatus;

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getSmsValidationCode() {
		return smsValidationCode;
	}

	public void setSmsValidationCode(String smsValidationCode) {
		this.smsValidationCode = smsValidationCode;
	}

	public Timestamp getSentTime() {
		return sentTime;
	}

	public void setSentTime(Timestamp sentTime) {
		this.sentTime = sentTime;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public int getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(int smsStatus) {
		this.smsStatus = smsStatus;
	}
}
