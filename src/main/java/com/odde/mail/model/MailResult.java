package com.odde.mail.model;

public class MailResult {
    private String status;

    public MailResult() {
    }

    public MailResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
