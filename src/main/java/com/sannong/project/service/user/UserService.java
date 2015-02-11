package com.sannong.project.service.user;

import com.sannong.project.domain.user.User;
import com.sannong.project.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Integer pageNumber, Integer perPage) {
        PageRequest pageRequest =
                new PageRequest(pageNumber - 1, perPage, Sort.Direction.DESC, "creationTime");
        return userRepository.findAll(pageRequest);
    }

    public User getUser(Long userId){
        return userRepository.findOne(userId);
    }

    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
