package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.region.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Repository("districtRepository")
public interface DistrictRepository extends CrudRepository<District, Long> {
    District findOne(Long districtId);
    List<District> findByCityId(Long cityId);
}
