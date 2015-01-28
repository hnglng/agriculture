package com.sannong.project.service.application;

import com.sannong.project.domain.application.QuestionnaireEntity;
import com.sannong.project.infrastructure.persistence.jpa.repositories.QuestionnaireJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Component
public class QuestionnaireRestService {
    @Autowired
    private QuestionnaireJpaRepository questionnaireJpaRepository;

    public QuestionnaireEntity findOne(Long questionnaireId){
        return questionnaireJpaRepository.findOne(questionnaireId);
    }
}
