package com.sannong.presentation.command;

import com.sannong.domain.project.Questionnaire;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.user.User;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class CreateApplicationCommand {
    private User user;
    private Questionnaire questionnaire;
    private SMS sms;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }
}
