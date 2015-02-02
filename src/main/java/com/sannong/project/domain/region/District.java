package com.sannong.project.domain.region;

import java.io.Serializable;

/**
 * Created by Bright Huang on 10/24/14.
 */
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long districtId;
    private String districtName;
    private String districtCode;
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
