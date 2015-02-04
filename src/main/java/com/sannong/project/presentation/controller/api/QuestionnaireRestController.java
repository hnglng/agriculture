package com.sannong.project.presentation.controller.api;

import com.sannong.project.domain.application.Question;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(Question.class)
@RequestMapping(value = "/api/questionnaires", produces = "application/hal+json")
public class QuestionnaireRestController {
    /*
    @Autowired
    private QuestionnaireRestService questionnaireRestService;

    @RequestMapping(value = "/{questionnaireId}", method = RequestMethod.GET)
    public QuestionnaireResource readQuestionnaire(@PathVariable Long questionnaireId) {
        Link link = linkTo(QuestionnaireRestController.class).withSelfRel();

        QuestionnaireEntity questionnaire = questionnaireRestService.findOne(questionnaireId);

        QuestionnaireResource resource = new QuestionnaireResource(questionnaire, link);
        return resource;
    }
    */
}