package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.QuestionnaireEntity;
import com.sannong.project.presentation.controller.QuestionnaireRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionnaireResourceAssembler
        extends ResourceAssemblerSupport<QuestionnaireEntity, QuestionnaireResource> {
    public QuestionnaireResourceAssembler() {
        super(QuestionnaireRestController.class, QuestionnaireResource.class);
    }

    @Override
    public QuestionnaireResource toResource(QuestionnaireEntity questionnaire) {
        QuestionnaireResource resource = createResourceWithId(questionnaire.getQuestionnaireId(), questionnaire);
        return resource;
    }

    @Override
    protected QuestionnaireResource instantiateResource(QuestionnaireEntity questionnaire) {
        return new QuestionnaireResource(questionnaire);
    }
}
