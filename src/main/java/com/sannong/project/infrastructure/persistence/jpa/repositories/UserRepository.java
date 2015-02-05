package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findOne(Long userId);

    List<User> findAll();

    User findByMobilePhone(String mobilePhone);

    User findByMobilePhoneAndRealName(String mobilePhone, String realName);

}
