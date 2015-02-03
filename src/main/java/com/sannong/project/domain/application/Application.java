package com.sannong.project.domain.application;

import com.sannong.project.domain.user.User;
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
public class Application {
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="application_id")
    private Long applicationId;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<Questionnaire> questionnaires;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
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
