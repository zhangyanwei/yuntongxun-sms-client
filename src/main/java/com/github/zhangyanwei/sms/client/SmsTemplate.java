package com.github.zhangyanwei.sms.client;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Date;

public class SmsTemplate {

    private String id;
    private String title;
    private String content;
    private Status status;
    private Type type;
    private Date dateCreated;
    private Date dateUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public enum Status {

        AUDITING(0), AUDITED(1), AUDIT_FAILED(2), DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @JsonCreator
        public static Status fromValue(int value) {
            for (Status status : Status.values()) {
                if (status.value == value) {
                    return status;
                }
            }

            throw new IllegalArgumentException("invalid status value " + value);
        }
    }

    public enum Type {

        VARIFICATION_CODE(0), NOTIFICATION(1), MARKETING_MESSAGE(2);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @JsonCreator
        public static Type fromValue(int value) {
            for (Type type : Type.values()) {
                if (type.value == value) {
                    return type;
                }
            }

            throw new IllegalArgumentException("invalid type value " + value);
        }
    }

}
