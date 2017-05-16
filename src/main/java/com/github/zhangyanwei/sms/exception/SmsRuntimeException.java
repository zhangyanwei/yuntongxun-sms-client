package com.github.zhangyanwei.sms.exception;

import static java.lang.String.format;

public abstract class SmsRuntimeException extends AbstractRuntimeException {

    private static final long serialVersionUID = -6045759006673606027L;

    public SmsRuntimeException(Message message) {
        super(message);
    }

    public SmsRuntimeException(Enum<?> code) {
        super(code);
    }

    public SmsRuntimeException(Enum<?> code, Throwable cause) {
        super(code, cause);
    }

    SmsRuntimeException() {

    }

    @Override
    protected final String messageResourceBaseName() {
        return "exception.sms";
    }

    @Override
    protected final String moduleName() {
        return format("SMS_%s", reasonCode());
    }

    protected abstract String reasonCode();
}
