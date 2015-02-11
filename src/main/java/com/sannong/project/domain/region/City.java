package com.sannong.project.domain.region;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Bright Huang on 10/24/14.
 */
@Entity
@Table(name = "cities")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="city_id")
    private Long cityId;
    @Column(name="city_name")
    private String cityName;
    @Column(name="city_code")
    private String cityCode;
    @Column(name="province_id")
    private Long provinceId;

    @OneToMany
    @JoinColumn(name="city_id", referencedColumnName = "city_id")
    private List<District> districts;

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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
