package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.QuestionEntity;
import com.sannong.project.domain.application.QuestionnaireEntity;
import com.sannong.project.domain.resource.QuestionnaireResource;
import com.sannong.project.service.application.QuestionnaireRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(QuestionEntity.class)
@RequestMapping(value = "/questionnaires", produces = "application/hal+json")
public class QuestionnaireRestController {
    @Autowired
    private QuestionnaireRestService questionnaireRestService;

    @RequestMapping(value = "/{questionnaireId}", method = RequestMethod.GET)
    public QuestionnaireResource readQuestionnaire(@PathVariable Long questionnaireId) {
        Link link = linkTo(QuestionnaireRestController.class).withSelfRel();

        QuestionnaireEntity questionnaire = questionnaireRestService.findOne(questionnaireId);

        QuestionnaireResource resource = new QuestionnaireResource(questionnaire, link);
        return resource;
    }
}