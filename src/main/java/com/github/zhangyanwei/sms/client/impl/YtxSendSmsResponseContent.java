package com.github.zhangyanwei.sms.client.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YtxSendSmsResponseContent implements YtxResponse.Content {

    private String dateCreated;
    private String smsMessageSid;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSmsMessageSid() {
        return smsMessageSid;
    }

    public void setSmsMessageSid(String smsMessageSid) {
        this.smsMessageSid = smsMessageSid;
    }
}