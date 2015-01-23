
package com.sannong.project.domain.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository {
	List<User> getUserByCondition(Map<String,Object> map);

    List<User> getUserByFuzzyMatch(Map<String,Object> map);

    Integer getUserTotalCount(Map<String,Object> map);

    String getUserNameByMobilePhone(String mobilePhone);

    void addUser(User user);

    void addUserAuthority(Map<String,Object> authorityMap);

    void updateUser(User user);

    void updatePassword(User user);

}

