package com.sannong.service;

import java.util.List;
import java.util.Map;

import com.sannong.domain.applications.Answer;
import com.sannong.domain.applications.Application;
import com.sannong.domain.applications.Question;

public interface IProjectService {

    void makeApplication(Application application) throws Exception;

    List<Question> getQuestionsByQuestionnaireNumber(Integer number);

    boolean updateAnswersAndComment(Answer answer) throws Exception;

    int getTotalQuestions();
}
