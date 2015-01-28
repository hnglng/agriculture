package com.sannong.project.domain.resource;

import com.sannong.project.domain.application.QuestionEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionResource extends Resource {

    public QuestionResource(QuestionEntity question) {
        super(question);
    }

    public QuestionResource(QuestionEntity question, Link... links) {
        super(question, links);
    }
}
