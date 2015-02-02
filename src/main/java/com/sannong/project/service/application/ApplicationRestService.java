package com.sannong.project.service.application;

import com.sannong.project.domain.application.Application;
import com.sannong.project.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Component
@Transactional
public class ApplicationRestService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application findOne(Long applicationId){
        return applicationRepository.findOne(applicationId);
    }

    public Application save(Application application){
        return applicationRepository.save(application);
    }


}
