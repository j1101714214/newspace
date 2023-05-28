package edu.whu.newspace.exception;

import lombok.Data;

/**
 * @author Newspace
 * @version 1.0
 * @description 统一格式返回错误
 * @date 2023/5/24 16:03
 */
@Data
public class ErrorResponse {
   private String msg;
   private Object body;

    public ErrorResponse(String msg) {
        this.msg = msg;
    }

    public ErrorResponse(String msg, Object body) {
        this.msg = msg;
        this.body = body;
    }

}
