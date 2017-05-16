package com.github.zhangyanwei.sms.exception;


public class YtxSmsRuntimeException extends SmsRuntimeException {

    private static final long serialVersionUID = -3135032782563557692L;

    public YtxSmsRuntimeException(String error) {
        super.setCodeValue(error);
    }

    @Override
    protected String reasonCode() {
        return "RUNTIME";
    }
}
