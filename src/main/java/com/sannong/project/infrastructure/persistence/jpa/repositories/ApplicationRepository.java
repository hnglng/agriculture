package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Repository("applicationRepository")
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    //Collection<ApplicationEntity> findByUserUserName(String username);

    Application findOne(Long applicationId);

    List<Application> findAll();

    Application save(Application application);

    Application findByUserUserId(Long userId);

}
