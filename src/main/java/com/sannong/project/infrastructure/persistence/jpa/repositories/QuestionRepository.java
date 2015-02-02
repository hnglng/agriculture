package com.sannong.project.infrastructure.persistence.jpa.repositories;

import com.sannong.project.domain.application.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Repository("questionRepository")
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByQuestionnaireNumber(Integer questionnaireNumber);
}