package com.sannong.service;

import java.util.List;

import com.sannong.domain.project.Application;
import com.sannong.domain.project.Question;
import com.sannong.domain.project.Questionnaire;

public interface IProjectService {
    Application getApplicationBy(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void addApplication(Application application) throws Exception;

}
