package com.sannong.project.presentation.controller;

import com.sannong.project.domain.region.City;
import com.sannong.project.domain.region.District;
import com.sannong.project.domain.region.Province;
import com.sannong.project.service.region.IRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 11/9/14.
 */
@Controller
public class RegionController {
    @Resource
    private IRegionService regionService;

    @RequestMapping(value = "getProvinces")
    public @ResponseBody List<Province> getProvinces() {
        return regionService.getProvinces();
    }

    @RequestMapping(value = "getCities")
    public @ResponseBody List<City> getCities(Long provinceIndex) {
        return regionService.getCities(provinceIndex);
    }

    @RequestMapping(value = "getDistricts")
    public @ResponseBody List<District> getDistricts(Long cityIndex) {
        return regionService.getDistricts(cityIndex);
    }

    @RequestMapping(value = "getCitiesWithDistricts")
    public @ResponseBody
    Map<String, Object> getCitiesWithDistrict(Long provinceIndex) {
        Map<String, Object> models = new HashMap<String, Object>();
        List<City> cities = regionService.getCities(provinceIndex);
        if (!cities.isEmpty()){
            models.put("cities", cities);
            City city = cities.get(0);
            List<District> districts = getDistricts(city.getCityId());
            if (!districts.isEmpty()){
                models.put("districts", districts);
            }
        }

        return models;
    }

}
