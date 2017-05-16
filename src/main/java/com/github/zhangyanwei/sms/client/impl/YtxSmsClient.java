package com.github.zhangyanwei.sms.client.impl;

import com.github.zhangyanwei.sms.client.SmsClient;
import com.github.zhangyanwei.sms.client.SmsTemplate;
import com.github.zhangyanwei.sms.exception.YtxSmsRuntimeException;
import com.google.common.base.Charsets;
import com.github.zhangyanwei.sms.client.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.google.common.hash.Hashing.md5;
import static com.google.common.io.BaseEncoding.base64;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Service
public class YtxSmsClient implements SmsClient {

    private static final String SUCCESS_CODE = "000000";
    private static final String SMS_SERVER_INNER_ERROR_CODE = "111000";

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private YtxSmsClientSettings settings;

    public void sendSmsMessage(SmsRequest templateMessage) throws YtxSmsRuntimeException {
        YtxSms message = new YtxSms(templateMessage);
        exchange("/SMS/TemplateSMS", message, YtxSendSmsResponse.class);
    }

    @Override
    public List<? extends SmsTemplate> findSmsTemplates(String templateId) throws YtxSmsRuntimeException {
        YtxSmsTemplate template = new YtxSmsTemplate();
        template.setTemplateId(templateId);
        YtxTemplateSearchResponse searchResponse = exchange("/SMS/QuerySMSTemplate", template, YtxTemplateSearchResponse.class);
        return searchResponse.getContent();
    }

    public <R extends YtxResponse, B extends YtxSmsTemplate> R exchange(String function, B request, Class<R> responseType) throws YtxSmsRuntimeException {
        String timestamp = DATE_FORMAT.format(new Date());
        request.setAppId(settings.getAppId());

        RestTemplate template = new RestTemplate();
        HttpEntity<B> entity = generateHttpEntityWithAuthenticationHeader(request, timestamp);
        ResponseEntity<R> response = template.postForEntity(generateUrl(function, timestamp), entity, responseType);
        checkYtxResponse(response);

        return response.getBody();
    }

    private String generateUrl(String func, String timestamp) {
        String url = format("/Accounts/%s/%s?sig=%s", settings.getAccountSid(), func, generateSigParameter(timestamp));
        return settings.getBaseUrl() + url;
    }

    private <T> HttpEntity<T> generateHttpEntityWithAuthenticationHeader(T entity, String timestamp) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON_UTF8));
        headers.setContentType(APPLICATION_JSON_UTF8);

        String plainAuthenticationStr = format("%s:%s", settings.getAccountSid(), timestamp);
        headers.set("Authorization", base64().encode(plainAuthenticationStr.getBytes(Charsets.UTF_8)));

        return new HttpEntity<>(entity, headers);
    }

    private String generateSigParameter(String timestamp) {
        String sigParam = settings.getAccountSid() + settings.getAuthToken() + timestamp;
        return md5().hashString(sigParam, Charsets.UTF_8).toString().toUpperCase();
    }

    private void checkYtxResponse(ResponseEntity<? extends YtxResponse> response) throws YtxSmsRuntimeException {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new YtxSmsRuntimeException(SMS_SERVER_INNER_ERROR_CODE);
        }

        YtxResponse ytxResponse = response.getBody();
        String statusCode = ytxResponse.getStatusCode();
        if (!SUCCESS_CODE.equals(statusCode)) {
            throw new YtxSmsRuntimeException(findMappedErrorCode(statusCode));
        }
    }

    private String findMappedErrorCode(@Nonnull String statusCode) {
        if (statusCode.matches("1110(0[1-9]|[1-9][0-9])")) {
            return "111001~111099";
        }

        if (statusCode.matches("1600(0[1-9]|10)")) {
            return "160001~160010";
        }

        return statusCode;
    }

}
