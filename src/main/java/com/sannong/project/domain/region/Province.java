package com.sannong.project.domain.region;

import java.io.Serializable;

/**
 * Created by Bright Huang on 10/24/14.
 */
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long provinceId;
    private String provinceName;
    private String provinceCode;

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

}
