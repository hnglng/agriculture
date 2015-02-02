package com.sannong.project.service.application;

import com.sannong.project.domain.application.Question;
import com.sannong.project.infrastructure.persistence.jpa.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bright Huang on 1/28/15.
 */
@Component
@Transactional
public class QuestionRestService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findByQuestionnaireNumber(Integer questionnaireNumber){
        return questionRepository.findByQuestionnaireNumber(questionnaireNumber);
    }

}
