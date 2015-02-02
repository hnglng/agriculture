package com.sannong.project.infrastructure.util;

import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class AppConfigReader {
    private static final String APP_CONFIG_FILE_NAME = "application.properties";
    private static final String SESSION_SMS_CODES = "session_sms_codes";
    private static final Properties properties = new Properties();

    public AppConfigReader() throws Exception{
        properties.load(this.getClass().getClassLoader().getResourceAsStream(APP_CONFIG_FILE_NAME));
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public String getSessionSmsCodes() {
        return SESSION_SMS_CODES;
    }
}