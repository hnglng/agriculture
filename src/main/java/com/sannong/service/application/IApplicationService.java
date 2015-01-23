package com.sannong.service.application;

import java.util.List;

import com.sannong.domain.application.ApplicationEntity;
import com.sannong.domain.application.Question;
import com.sannong.domain.application.Questionnaire;
import com.sannong.presentation.command.CreateApplicationCommand;

public interface IApplicationService {
    ApplicationEntity getApplicationBy(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void createApplication(CreateApplicationCommand createApplicationCommand) throws Exception;

}
