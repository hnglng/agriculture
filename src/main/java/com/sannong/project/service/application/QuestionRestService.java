package com.sannong.project.service.application;

import com.sannong.project.domain.application.QuestionEntity;
import com.sannong.project.infrastructure.persistence.jpa.repositories.QuestionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Component
public class QuestionRestService {
    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    public List<QuestionEntity> findByQuestionnaireNumber(Integer questionnaireNumber){
        return questionJpaRepository.findByQuestionnaireNumber(questionnaireNumber);
    }
}
