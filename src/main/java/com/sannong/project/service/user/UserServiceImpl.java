package com.sannong.project.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.project.domain.user.User;
import com.sannong.project.domain.user.UserRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserByCondition(Map<String, Object> map) {
        return userRepository.getUserByCondition(map);
    }

    public List<User> getUserByFuzzyMatch(Map<String, Object> map) {
        return userRepository.getUserByFuzzyMatch(map);
    }

    public Integer getUserTotalCount(Map<String, Object> map) throws Exception {
        return userRepository.getUserTotalCount(map);
    }

    public String getUserNameByMobilePhone(String mobilePhone) throws Exception{
        return userRepository.getUserNameByMobilePhone(mobilePhone);
    }

    public void updatePassword(User user) throws Exception {
        userRepository.updatePassword(user);
    }

    public void updateUser(User user) throws Exception {
        userRepository.updateUser(user);
    }
}
