package com.sannong.project.service.region;

import com.sannong.project.domain.region.City;
import com.sannong.project.domain.region.District;
import com.sannong.project.domain.region.Province;
import com.sannong.project.domain.region.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 10/26/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Province> getProvinces() {
        return regionRepository.getProvinces();
    }

    public List<City> getCities(Long provinceId) {
        return regionRepository.getCitiesByProvinceId(provinceId);
    }

    public List<District> getDistricts(Long cityId) {
        return regionRepository.getDistrictsByCityId(cityId);
    }

}