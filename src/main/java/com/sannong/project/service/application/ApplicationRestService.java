package com.sannong.project.service.application;

import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Component
public class ApplicationRestService {
    @Autowired
    private ApplicationJpaRepository applicationJpaRepository;

    public List<ApplicationEntity> findAll() {
        return applicationJpaRepository.findAll();
    }

    public ApplicationEntity findOne(Long applicationId){
        return applicationJpaRepository.findOne(applicationId);
    }
}
