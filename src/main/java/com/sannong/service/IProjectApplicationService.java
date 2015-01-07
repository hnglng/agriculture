package com.sannong.service;

import java.util.List;
import java.util.Map;

import com.sannong.domain.applications.Application;
import com.sannong.domain.applications.Question;
import com.sannong.domain.applications.Questionnaire;

public interface IProjectApplicationService {
    Application getApplicationBy(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void addApplication(Application application) throws Exception;

}
