package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.region.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Repository("provinceRepository")
public interface ProvinceRepository extends CrudRepository<Province, Long> {
    Province findOne(Long provinceId);
    List<Province> findAll();
}
