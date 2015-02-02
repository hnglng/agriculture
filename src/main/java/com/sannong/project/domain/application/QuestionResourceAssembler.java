package com.sannong.project.domain.application;

import com.sannong.project.presentation.controller.QuestionRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionResourceAssembler extends ResourceAssemblerSupport<Question, QuestionResource> {
    public QuestionResourceAssembler() {
        super(QuestionRestController.class, QuestionResource.class);
    }

    @Override
    public QuestionResource toResource(Question question) {
        QuestionResource resource = createResourceWithId(question.getQuestionId(), question);
        return resource;
    }

    @Override
    protected QuestionResource instantiateResource(Question question) {
        return new QuestionResource(question);
    }
}
