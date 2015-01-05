package com.sannong.service;

import java.util.List;
import java.util.Map;

import com.sannong.domain.user.User;

public interface IUserService {
	
    public List<User> getUserByCondition(Map<String, Object> map);
    
    public List<User> getUserByFuzzyMatch(Map<String, Object> map);

    public Integer getUserTotalCount(Map<String,Object> map) throws Exception;

    public String getUserNameByMobilePhone(String mobilePhone) throws Exception;

    public void updateUser(User user) throws Exception;

    public void updatePassword(User user) throws Exception;
}
