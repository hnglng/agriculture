package com.sannong.project.service.application;

import java.util.List;

import com.sannong.project.domain.application.ApplicationEntity;
import com.sannong.project.domain.application.Question;
import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.presentation.command.CreateApplicationCommand;

public interface IApplicationService {
    ApplicationEntity getApplicationBy(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void createApplication(CreateApplicationCommand createApplicationCommand) throws Exception;

}
