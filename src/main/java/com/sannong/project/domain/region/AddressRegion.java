package com.sannong.project.domain.region;

import java.util.List;

/**
 * Created by Bright Huang on 2/9/15.
 */
public class AddressRegion {
    private Province province;
    private List<City> cities;
    private List<District> districts;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
