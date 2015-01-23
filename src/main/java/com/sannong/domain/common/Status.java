package com.sannong.domain.common;

/**
 * Created by Bright Huang on 11/29/14.
 */
public enum Status {
    OK(1000, "成功"),
    PASSWORD_UPDATED(1001, "密码已更新"),
    LOGIN_SUCCESS(1002, "登录成功"),
    PASSWORD_SENT(1003, "新密码已发送"),
    CAPTCHA_SENT(1004, "验证码已发送"),
    //////////////////////////////////
    FAILURE(2000, "失败"),
    USER_NOT_FOUND(2001, "用户不存在"),
    PASSWORD_INCORRECT(2002, "密码不正确"),
    OLD_PASSWORD_MISMATCH(2003, "旧密码不匹配"),
    CONFIRMED_PASSWORD_MISMATCH(2004, "新密码与确认密码不匹配"),
    LOGIN_FAILED(2005, "登录失败"),
    CAPTCHA_INCORRECT(2006, "验证码不正确"),
    NEW_PASSWORD_INCORRECT(2007, "新密码不正确"),
    NAME_MISMATCH(2008, "姓名不匹配"),
    NAME_OR_CELLPHONE_NOT_FOUND(2009, "姓名或手机号码不存在"),
    NAME_OR_CELLPHONE_MISMATCH(2010, "姓名或手机号码不匹配"),
    PASSWORD_UNSENT(2011, "新密码发送失败"),
    CELLPHONE_EXISTED(2012, "手机号码已存在"),
    CAPTCHA_UNSENT(2013, "验证码发送失败"),
    CELLPHONE_IS_NULL(2014, "手机号码为空"),
    USERNAME_OR_PASSWORD_ERROR(2015, "用户名或密码错误");

    private int code;
    private String message;

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
