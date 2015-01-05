package com.sannong.domain.region;

import java.io.Serializable;

/**
 * Created by Bright Huang on 10/24/14.
 */
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cityId;
    private String cityName;
    private String cityCode;
    private Long provinceId;


    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}
