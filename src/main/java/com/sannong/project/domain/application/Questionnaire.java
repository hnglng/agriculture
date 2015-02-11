package com.sannong.project.domain.application;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
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
    @Column(name="application_id")
    private Long applicationId;


    //@ManyToOne
    //@JoinColumn(name = "application_id")
    //private Application application;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    /*
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    */

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

}
