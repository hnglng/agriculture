package com.sannong.service.region;

import com.sannong.domain.region.City;
import com.sannong.domain.region.District;
import com.sannong.domain.region.Province;

import java.util.List;

/**
 * Created by Bright Huang on 10/26/14.
 */
public interface IRegionService {
    public List<Province> getProvinces();
    public List<City> getCities(Long provinceId);
    public List<District> getDistricts(Long cityId);
}
