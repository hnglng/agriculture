package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.QuestionnaireEntity;
import com.sannong.project.presentation.controller.QuestionnaireRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionnaireResource extends Resource {

    public QuestionnaireResource(QuestionnaireEntity questionnaire) {
        super(questionnaire);
    }

    public QuestionnaireResource(QuestionnaireEntity questionnaire, Link... links) {
        super(questionnaire, links);
        add(linkTo(methodOn(QuestionnaireRestController.class)
                .readQuestionnaire(questionnaire.getQuestionnaireId())).withSelfRel());
    }
}
