package com.sannong.project.service.user;

import com.sannong.project.domain.user.UserEntity;
import com.sannong.project.infrastructure.persistence.jpa.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Component
public class UserRestService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }

    public UserEntity findOne(Long userId){
        return userJpaRepository.findOne(userId);
    }
}
