package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.QuestionEntity;
import com.sannong.project.presentation.controller.QuestionRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionResourceAssembler extends ResourceAssemblerSupport<QuestionEntity, QuestionResource> {
    public QuestionResourceAssembler() {
        super(QuestionRestController.class, QuestionResource.class);
    }

    @Override
    public QuestionResource toResource(QuestionEntity question) {
        QuestionResource resource = createResourceWithId(question.getQuestionId(), question);
        return resource;
    }

    @Override
    protected QuestionResource instantiateResource(QuestionEntity question) {
        return new QuestionResource(question);
    }
}
