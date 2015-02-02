package com.sannong.project.domain.application;

import com.sannong.project.domain.application.Questionnaire;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionnaireResource extends Resource {


    public QuestionnaireResource(Questionnaire questionnaire) {
        super(questionnaire);
    }

    public QuestionnaireResource(Questionnaire questionnaire, Link... links) {
        super(questionnaire, links);
//        add(linkTo(methodOn(QuestionnaireRestController.class)
//                .readQuestionnaire(questionnaire.getQuestionnaireId())).withSelfRel());
    }

}
