package com.sannong.project.domain.application;

import com.sannong.project.domain.application.Question;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bright Huang on 1/28/15.
 */
public class QuestionResource extends Resource {

    public QuestionResource(Question question) {
        super(question);
    }

    public QuestionResource(Question question, Link... links) {
        super(question, links);
    }
}
