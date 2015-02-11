package com.sannong.project.domain.region;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Bright Huang on 10/24/14.
 */
@Entity
@Table(name = "provinces")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="province_id")
    private Long provinceId;
    @Column(name="province_name")
    private String provinceName;
    @Column(name="province_code")
    private String provinceCode;

    @OneToMany
    @JoinColumn(name="province_id", referencedColumnName = "province_id")
    private List<City> cities;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
