package com.sannong.project.presentation.command;

import com.sannong.project.domain.sms.SMS;
import com.sannong.project.domain.user.User;

import java.util.List;

/**
 * Created by Bright Huang on 1/23/15.
 */
public class CreateApplicationCommand {
    private User user;
    private List<String> answers;
    private SMS sms;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }
}
