package com.sannong.project.domain.common;

/**
 * Created by Bright Huang on 1/22/15.
 */
public class Error {
    private Integer code;
    private String message;
    private String field;

    public Error(){}

    public Error(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Error(Integer code, String message, String field){
        this.code = code;
        this.message = message;
        this.field = field;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
