package com.sannong.project.domain.user;

import com.sannong.project.infrastructure.util.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Bright Huang on 1/27/15.
 */
@Entity
@Table( name = "users" )
public class UserEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="username")
    private String userName;
    @Column
    private String password;
    @Column(name="real_name")
    private String realName;
    @Column
    private String mailbox;
    @Column(name="company_name")
    private String companyName;
    @Column(name="company_province")
    private Long companyProvince;
    @Column(name="company_city")
    private Long companyCity;
    @Column(name="company_district")
    private Long companyDistrict;
    @Column(name="company_address")
    private String companyAddress;
    @Column(name="mobile_phone")
    private String mobilePhone;
    @Column(name="desk_phone")
    private String deskPhone;
    @Column(name="job_title")
    private String jobTitle;
    @Column
    private Integer enabled;
    @Column(name="creation_time")
    private Timestamp creationTime;
    @Column(name="last_updated")
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
