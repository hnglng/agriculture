package com.sannong.project.domain.sms;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Bright Huang
 */
@Entity
public class SMS implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sms_id")
	private Long smsId;
    @Column(name="sms_validation_code")
	private String smsValidationCode;
    @Column(name="sent_time")
	private Timestamp sentTime;
    @Column(name="mobile_phone")
	private String mobilePhone;
    @Column(name="sms_content")
	private String smsContent;
    @Column(name="sms_status")
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
