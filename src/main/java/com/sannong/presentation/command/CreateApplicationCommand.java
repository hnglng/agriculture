package com.sannong.presentation.command;

import com.sannong.domain.application.Questionnaire;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.user.User;

import java.util.List;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class CreateApplicationCommand {
    private User user;
    private List<Questionnaire> questionnaires;
    private SMS sms;

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

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }
}
