package com.sannong.project.service.application;

import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Component
public class ApplicationRestService {
    @Autowired
    private ApplicationJpaRepository applicationJpaRepository;

    public Collection<ApplicationEntity> findByUserUserName(String userName) {
        //return applicationJpaRepository.findByUserUserName(userName);
        return null;

    }

    public ApplicationEntity findOne(Long applicationId){
        return applicationJpaRepository.findOne(applicationId);
    }
}
