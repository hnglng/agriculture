package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Repository("ApplicationJpaRepository")
public interface ApplicationJpaRepository extends CrudRepository<ApplicationEntity, Long> {
    //Collection<ApplicationEntity> findByUserUserName(String username);

    ApplicationEntity findOne(Long applicationId);

    List<ApplicationEntity> findAll();

}
