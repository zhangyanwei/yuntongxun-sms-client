package com.github.zhangyanwei.sms.client.impl;

import com.github.zhangyanwei.sms.client.SmsRequest;

import static com.google.common.base.Joiner.on;

public class YtxSms extends YtxSmsTemplate {

    private String to;
    private String[] datas;

    public YtxSms(SmsRequest templateMessage) {
        this.setTemplateId(templateMessage.getTemplateId());
        this.to = on(',').join(templateMessage.getPhoneNumbers());
        this.datas = templateMessage.getParameters();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String[] getDatas() {
        return datas;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }
}
