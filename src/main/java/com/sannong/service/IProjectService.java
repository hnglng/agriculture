package com.sannong.service;

import java.util.Map;

import com.sannong.domain.applications.Answer;
import com.sannong.domain.applications.Application;

public interface IProjectService {

    void projectApplication(Application application) throws Exception;

    Answer getQuestionnaireAndAnswerByCondition(Map<String, Object> map);

    boolean updateAnswersAndComment(Answer answer) throws Exception;

    int getTotalQuestions();
}
