package com.github.zhangyanwei.sms.client.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.zhangyanwei.sms.client.SmsTemplate;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YtxTemplateSearchResponseContent extends SmsTemplate implements YtxResponse.Content {

    @Override
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    public void setDateCreated(Date dateCreated) {
        super.setDateCreated(dateCreated);
    }

    @Override
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    public void setDateUpdated(Date dateUpdated) {
        super.setDateUpdated(dateUpdated);
    }
}
