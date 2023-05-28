package edu.whu.newspace.constant;

public enum CommentStatus {
    WAITING_FOR_AUDIT_CODE("400100"),
    REJECT_AUDIT_CODE("400101"),
    ACCEPT_AUDIT_CODE("400102");

    private String code;
    CommentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
