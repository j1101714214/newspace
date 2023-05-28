package edu.whu.newspace.constant;

/**
 * @author Newspace
 * @version 1.0
 * @description 博客审核状态
 * @date 2023/5/24 16:24
 */
public enum BlogsStatus {
    WAITING_FOR_AUDIT_CODE("400100"),
    REJECT_AUDIT_CODE("400101"),
    ACCEPT_AUDIT_CODE("400102");

    private String code;
    BlogsStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
