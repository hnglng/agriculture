package com.sannong.project.domain.application;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Bright Huang on 1/27/15.
 */
@Entity
@Table( name = "questions" )
/*
@NamedQueries({
    @NamedQuery(name = "findByQuestionnaireNumber",
            query = "select u from QuestionEntity u where u.questionnaireNumber  = :questionnaireNumber")
})
*/
public class Question implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Long questionId;
    @Column
    private String option1;
    @Column
    private String option2;
    @Column
    private String option3;
    @Column
    private String option4;
    @Column
    private String option5;
    @Column(name="question_content")
    private String questionContent;
    @Column(name="questionnaire_number")
    private Integer questionnaireNumber;
    @Column(name="single_selection_only")
    private Integer singleSelectionOnly;

//    @ManyToMany(mappedBy = "questions")
//    public List<Questionnaire> questionnaires;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getQuestionnaireNumber() {
        return questionnaireNumber;
    }

    public void setQuestionnaireNumber(Integer questionnaireNumber) {
        this.questionnaireNumber = questionnaireNumber;
    }

    public Integer getSingleSelectionOnly() {
        return singleSelectionOnly;
    }

    public void setSingleSelectionOnly(Integer singleSelectionOnly) {
        this.singleSelectionOnly = singleSelectionOnly;
    }

//    public List<Questionnaire> getQuestionnaires() {
//        return questionnaires;
//    }
//
//    public void setQuestionnaires(List<Questionnaire> questionnaires) {
//        this.questionnaires = questionnaires;
//    }
}
