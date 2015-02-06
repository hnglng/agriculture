package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Repository("questionnaireRepository")
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
    Questionnaire findOne(Long questionnaireId);

    List<Questionnaire> findByApplicationApplicationId(Long applicationId);
}
