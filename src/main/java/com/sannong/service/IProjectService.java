package com.sannong.service;

import java.util.List;

import com.sannong.domain.project.ApplicationEntity;
import com.sannong.domain.project.Question;
import com.sannong.domain.project.Questionnaire;
import com.sannong.presentation.command.CreateApplicationCommand;

public interface IProjectService {
    ApplicationEntity getApplicationBy(String userName);

    Questionnaire getQuestionnaire(Long applicationId, Integer questionnaireNumber);

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    int getTotalQuestions();

    void createApplication(CreateApplicationCommand createApplicationCommand) throws Exception;

}
