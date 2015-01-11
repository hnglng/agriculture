package com.sannong.presentation.model;




/**
 * Created by Bright Huang on 11/29/14.
 */
public class Response<T> {
    private Integer statusCode;
    private String statusMessage;
    private String URI;
    private T data;

    public Response() {}

    public Response(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Response(Integer statusCode, String statusMessage, String URI) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.URI = URI;
    }

    public Response(Integer statusCode, String statusMessage, String URI, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.URI = URI;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
