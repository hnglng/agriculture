package com.sannong.domain.region;

import com.sannong.domain.region.City;
import com.sannong.domain.region.District;
import com.sannong.domain.region.Province;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 10/24/14.
 */
@Repository
@Transactional
public interface RegionRepository {
	
    List<Province> getProvinces();

    List<City> getCitiesByProvinceId(Long provinceId);
    
    List<District> getDistrictsByCityId(Long cityId);

    Province getProvinceById(Long provinceId);

    City getCityById(Long cityId);

    District getDistrictById(Long districtId);


}
