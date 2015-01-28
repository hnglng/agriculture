package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Repository("QuestionJpaRepository")
public interface QuestionJpaRepository extends CrudRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByQuestionnaireNumber(Integer questionnaireNumber);
}