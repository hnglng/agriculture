package com.sannong.project.service.region;

import com.sannong.project.domain.region.City;
import com.sannong.project.domain.region.District;
import com.sannong.project.domain.region.Province;
import com.sannong.project.infrastructure.persistence.jpa.repositories.CityRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.DistrictRepository;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 10/26/14.
 */
@Service
@Transactional
public class RegionService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public List<Province> getProvinces() {
        return provinceRepository.findAll();
    }

    public Province getProvinceById(Long id){return provinceRepository.findOne(id); }

    public List<City> getCities(Long provinceId) {
        return cityRepository.findByProvinceId(provinceId);
    }

    public List<District> getDistricts(Long cityId) {
        return districtRepository.findByCityId(cityId);
    }
}
