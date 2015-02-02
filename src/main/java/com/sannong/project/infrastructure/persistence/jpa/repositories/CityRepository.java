package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.region.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Repository(("cityRepository"))
public interface CityRepository extends CrudRepository<City, Long> {
    City findOne(Long cityId);
    List<City> findByProvinceId(Long provinceId);
}
