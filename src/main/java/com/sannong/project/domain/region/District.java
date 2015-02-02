package com.sannong.project.domain.region;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Bright Huang on 10/24/14.
 */
@Entity
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="district_id")
    private Long districtId;
    @Column(name="district_name")
    private String districtName;
    @Column(name="district_code")
    private String districtCode;
    @Column(name="city_id")
    private Long cityId;


    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
