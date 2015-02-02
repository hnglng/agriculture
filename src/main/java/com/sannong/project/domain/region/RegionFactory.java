package com.sannong.project.domain.region;

import com.sannong.project.infrastructure.persistence.jpa.repositories.CityRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.DistrictRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Bright Huang on 11/14/14.
 */
@Component
public class RegionFactory {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;


    public Region build(Long provinceId, Long cityId, Long districtId){
        Province province = provinceRepository.findOne(provinceId);
        City city = cityRepository.findOne(cityId);
        District district = districtRepository.findOne(districtId);

        return new Region(province, city, district);

    }


}
