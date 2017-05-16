package com.github.zhangyanwei.sms.client;

import com.github.zhangyanwei.sms.exception.SmsRuntimeException;

import java.util.List;

public interface SmsClient {
    void sendSmsMessage(SmsRequest templateMessage) throws SmsRuntimeException;
    List<? extends SmsTemplate> findSmsTemplates(String templateId) throws SmsRuntimeException;
}
