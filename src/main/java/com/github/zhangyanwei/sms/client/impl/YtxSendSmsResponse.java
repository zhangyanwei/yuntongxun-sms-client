package com.github.zhangyanwei.sms.client.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YtxSendSmsResponse extends YtxResponse<YtxSendSmsResponseContent> {

    @Override
    @JsonSetter("templateSMS")
    public void setContent(YtxSendSmsResponseContent content) {
        super.setContent(content);
    }
}
