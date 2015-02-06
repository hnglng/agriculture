package com.sannong.project.presentation.controller.api;

import com.sannong.project.domain.application.Question;
import com.sannong.project.domain.application.Questionnaire;
import com.sannong.project.domain.application.QuestionnaireResource;
import com.sannong.project.domain.application.QuestionnaireResourceAssembler;
import com.sannong.project.service.application.QuestionnaireRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(Question.class)
@RequestMapping(value = "/api/questionnaires", produces = "application/hal+json")
public class QuestionnaireRestController {

    @Autowired
    private QuestionnaireRestService questionnaireRestService;


    @RequestMapping( method = RequestMethod.GET)
    public Resources<QuestionnaireResource> readQuestionnaires(@RequestParam Long applicationId) {
        Link link = linkTo(QuestionnaireRestController.class).withSelfRel();

        List<Questionnaire> questionnaires = questionnaireRestService.findByApplicationId(applicationId);

        List<QuestionnaireResource> questionnaireResources =
                new QuestionnaireResourceAssembler().toResources(questionnaires);

        Resources<QuestionnaireResource> resources = new Resources<QuestionnaireResource>(questionnaireResources, link);

        return resources;
    }


    @RequestMapping(value = "/{questionnaireId}", method = RequestMethod.GET)
    public QuestionnaireResource readQuestionnaire(@PathVariable Long questionnaireId) {
        Link link = linkTo(QuestionnaireRestController.class).withSelfRel();

        Questionnaire questionnaire = questionnaireRestService.findOne(questionnaireId);

        QuestionnaireResource resource = new QuestionnaireResource(questionnaire, link);
        return resource;
    }

}