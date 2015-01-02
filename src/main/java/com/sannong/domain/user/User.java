package com.sannong.domain.user;

import com.sannong.infrastructure.util.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Bright Huang
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 4891642085331481252L;
	
	private String userName;
	private String password;
	private String realName;
	private String mailbox;
	private String companyName;
    private Long companyProvince;
    private Long companyCity;
    private Long companyDistrict;
	private String companyAddress;
	private String mobilePhone;
	private String deskPhone;
	private String jobTitle;
	private Integer enabled;
	private Timestamp creationTime;
	private Timestamp lastUpdated;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyProvince() {
		return companyProvince;
	}

	public void setCompanyProvince(Long companyProvince) {
		this.companyProvince = companyProvince;
	}

	public Long getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(Long companyCity) {
		this.companyCity = companyCity;
	}

	public Long getCompanyDistrict() {
		return companyDistrict;
	}

	public void setCompanyDistrict(Long companyDistrict) {
		this.companyDistrict = companyDistrict;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
