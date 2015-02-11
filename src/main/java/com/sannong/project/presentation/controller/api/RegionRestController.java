package com.sannong.project.presentation.controller.api;

import com.sannong.project.domain.region.City;
import com.sannong.project.domain.region.District;
import com.sannong.project.domain.region.Province;
import com.sannong.project.domain.region.Region;
import com.sannong.project.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bright Huang on 11/9/14.
 */
@RestController
@ExposesResourceFor(Region.class)
@RequestMapping(value = "/api/regions", produces = "application/hal+json")
public class RegionRestController {
    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/provinces")
    public @ResponseBody List<Province> getProvinces() {
        return regionService.getProvinces();
    }

    @RequestMapping(value = "/provinces/{provinceId}")
    public @ResponseBody Province readProvince(@PathVariable Long provinceId) {
        return regionService.getProvinceById(provinceId);
    }


    @RequestMapping(value = "/provinces/{provinceId}/cities")
    public @ResponseBody List<City> getCities(@PathVariable Long provinceId) {
        return regionService.getCities(provinceId);
    }

    @RequestMapping(value = "/cities/{cityId}/districts")
    public @ResponseBody List<District> getDistricts(@PathVariable Long cityId) {
        return regionService.getDistricts(cityId);
    }

    /*
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
    */

}
