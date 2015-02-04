package com.sannong.project.domain.application;

import com.sannong.project.presentation.controller.api.QuestionnaireRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionnaireResourceAssembler
        extends ResourceAssemblerSupport<Questionnaire, QuestionnaireResource> {
    public QuestionnaireResourceAssembler() {
        super(QuestionnaireRestController.class, QuestionnaireResource.class);
    }

    @Override
    public QuestionnaireResource toResource(Questionnaire questionnaire) {
        QuestionnaireResource resource = createResourceWithId(questionnaire.getQuestionnaireId(), questionnaire);
        return resource;
    }

    @Override
    protected QuestionnaireResource instantiateResource(Questionnaire questionnaire) {
        return new QuestionnaireResource(questionnaire);
    }
}
