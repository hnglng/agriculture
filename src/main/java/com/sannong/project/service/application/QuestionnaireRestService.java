package com.sannong.project.service.application;

import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.infrastructure.persistence.jpa.repositories.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Component
@Transactional
public class QuestionnaireRestService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public Questionnaire findOne(Long questionnaireId){
        return questionnaireRepository.findOne(questionnaireId);
    }

    public List<Questionnaire> findByApplicationId(Long applicationId){
        return questionnaireRepository.findByApplicationId(applicationId);
    }
}
