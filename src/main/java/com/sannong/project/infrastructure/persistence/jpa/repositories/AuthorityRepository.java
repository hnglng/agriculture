package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.user.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bright Huang on 2/2/15.
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
