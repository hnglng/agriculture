package com.sannong.project.domain.application;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Bright Huang on 1/27/15.
 */
@Entity
@Table(name = "questionnaires")
@Relation(value = "questionnaire", collectionRelation = "questionnaires")
public class Questionnaire implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="questionnaire_id")
    private Long questionnaireId;
    @Column(name="application_id")
    private Long applicationId;
    @Column(name="questionnaire_number")
    private Integer questionnaireNumber;
    @Column
    private String answers;
    @Column(name="questionnaire_committed")
    private Boolean questionnaireCommitted;
    @Column(name="creation_time")
    private Timestamp creationTime;
    @Column(name="last_updated")
    private Timestamp lastUpdated;
    @ManyToMany
    @JoinTable(
            name="questionnaires_questions",
            joinColumns={@JoinColumn(name="questionnaire_id", referencedColumnName="questionnaire_id")},
            inverseJoinColumns={@JoinColumn(name="question_id", referencedColumnName="question_id")})
    private List<Question> questions;

    //private String concatenatedAnswers = "";


    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getQuestionnaireNumber() {
        return questionnaireNumber;
    }

    public void setQuestionnaireNumber(Integer questionnaireNumber) {
        this.questionnaireNumber = questionnaireNumber;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Boolean getQuestionnaireCommitted() {
        return questionnaireCommitted;
    }

    public void setQuestionnaireCommitted(Boolean questionnaireCommitted) {
        this.questionnaireCommitted = questionnaireCommitted;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /*
    public String getConcatenatedAnswers() {

        if (StringUtils.isNotBlank(concatenatedAnswers)){
            return concatenatedAnswers;
        } else if (answers == null){
            return "";
        } else if (answers.size() > 0){
            for (String answer : answers) {
                concatenatedAnswers = concatenatedAnswers + answer + ";";
            }
            return concatenatedAnswers.substring(0, concatenatedAnswers.lastIndexOf(";"));
        } else{
            return "";
        }
    }

    public void setConcatenatedAnswers(String concatenatedAnswers) {
        this.concatenatedAnswers = concatenatedAnswers;
    }
*/


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


}
