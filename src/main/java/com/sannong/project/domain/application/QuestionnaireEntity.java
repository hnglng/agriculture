package com.sannong.project.domain.application;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Bright Huang on 1/27/15.
 */
@Entity
@Table( name = "questionnaires" )
public class QuestionnaireEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="application_id")
    private Long applicationId;
    @Id
    @Column(name="questionnaire_number")
    private Integer questionnaireNumber;
    private String answers;
    @Column(name="questionnaire_committed")
    private Boolean questionnaireCommitted;
    @Column(name="creation_time")
    private Timestamp creationTime;
    @Column(name="last_updated")
    private Timestamp lastUpdated;
    //private String concatenatedAnswers = "";

    /*
    @OneToMany
    @JoinColumn(name="questionnaire_number", referencedColumnName="questionnaire_number")
    private List<QuestionEntity> questions;
    */

    /*
    @OneToMany
    @JoinColumn(name="questionnaire_number", referencedColumnName="questionnaire_number")
    private List<QuestionEntity> questions;
    */

    public QuestionnaireEntity() {
    }

    public QuestionnaireEntity(Long applicationId, Integer questionnaireNumber) {
        this.applicationId = applicationId;
        this.questionnaireNumber = questionnaireNumber;
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

    /*
    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
    */

}
