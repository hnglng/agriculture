package com.sannong.project.service.user;

import com.sannong.project.domain.user.User;
import com.sannong.project.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Service
@Transactional
public class UserRestService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    public User findByMobilePhone(String mobilePhone){
        return userRepository.findByMobilePhone(mobilePhone);
    }

    public User findByMobilePhoneAndRealName(String mobilePhone, String realName){
        return userRepository.findByMobilePhoneAndRealName(mobilePhone, realName);
    }

    public void save(User user){
        userRepository.save(user);
    }

}
