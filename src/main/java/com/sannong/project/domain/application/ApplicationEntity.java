package com.sannong.project.domain.application;

import com.sannong.project.domain.common.AbstractEntity;
import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.user.User;
import com.sannong.project.domain.user.UserEntity;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Bright Huang on 1/26/15.
 */
@Entity
@Table( name = "applications" )
@Relation(value = "application", collectionRelation = "applications")
public class ApplicationEntity {
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="application_id")
    private Long applicationId;
    @OneToOne
    @JoinColumn(name="username")
    private UserEntity user;

    @OneToMany
    @JoinColumn(name="application_id", referencedColumnName="application_id")
    private List<QuestionnaireEntity> questionnaires;


    @Column
    private String comments;
    @Column(name="creation_time")
    private Timestamp creationTime;


    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<QuestionnaireEntity> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<QuestionnaireEntity> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

}
