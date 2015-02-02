package com.sannong.project.service.region;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bright Huang on 10/26/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
//public class RegionServiceImpl implements IRegionService {
public class RegionService {
    /*
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
    */
}
