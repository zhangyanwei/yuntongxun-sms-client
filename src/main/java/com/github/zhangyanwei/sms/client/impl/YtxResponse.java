package com.github.zhangyanwei.sms.client.impl;

public class YtxResponse<T> {

    private String statusCode;
    private T content;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public interface Content {}

}
