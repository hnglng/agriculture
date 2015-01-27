package com.sannong.project.service.application;

import java.util.Collection;
import java.util.List;

import com.sannong.project.domain.application.Application;
import com.sannong.project.domain.application.Question;
import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.presentation.command.CreateApplicationCommand;

public interface IApplicationService {

    List<Application> findByUserName(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void createApplication(CreateApplicationCommand createApplicationCommand) throws Exception;

}
