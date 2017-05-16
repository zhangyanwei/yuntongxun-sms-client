package com.github.zhangyanwei.sms.client.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YtxTemplateSearchResponse extends YtxResponse<List<YtxTemplateSearchResponseContent>> {

    @Override
    @JsonSetter("TemplateSMS")
    public void setContent(List<YtxTemplateSearchResponseContent> content) {
        super.setContent(content);
    }
}
