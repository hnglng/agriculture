package com.sannong.project.presentation.controller;

import com.sannong.project.domain.application.QuestionEntity;
import com.sannong.project.domain.resource.QuestionResource;
import com.sannong.project.domain.resource.QuestionResourceAssembler;
import com.sannong.project.service.application.QuestionRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Bright Huang on 1/28/15.
 */
@RestController
@ExposesResourceFor(QuestionEntity.class)
@RequestMapping(value = "/questions", produces = "application/hal+json")
public class QuestionRestController {
    @Autowired
    private QuestionRestService questionRestService;


    @RequestMapping(value="/questionnaireNumbers/{questionnaireNumber}", method = RequestMethod.GET)
    public Resources<QuestionResource> readQuestions(@PathVariable Integer questionnaireNumber){
        Link link = linkTo(QuestionRestController.class).withSelfRel();
        List<QuestionEntity> questions = questionRestService.findByQuestionnaireNumber(questionnaireNumber);
        List<QuestionResource> questionResources =
                new QuestionResourceAssembler().toResources(questions);
        Resources<QuestionResource> resources = new Resources<QuestionResource>(questionResources, link);
        return resources;
    }
}
