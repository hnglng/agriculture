package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.QuestionnaireEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Repository("QuestionnaireJpaRepository")
public interface QuestionnaireJpaRepository extends CrudRepository<QuestionnaireEntity, Long> {
    QuestionnaireEntity findOne(Long questionnaireId);
}
