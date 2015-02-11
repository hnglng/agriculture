package com.sannong.project.domain.user;

import com.sannong.project.domain.region.City;
import com.sannong.project.domain.region.District;
import com.sannong.project.domain.region.Province;
import com.sannong.project.infrastructure.util.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Bright Huang on 1/27/15.
 */
@Entity
@Table( name = "users" )
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;
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

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="province_id")
    private Province province;
    @OneToOne
    @JoinColumn(name="city_id")
    private City city;
    @OneToOne
    @JoinColumn(name="district_id")
    private District district;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
