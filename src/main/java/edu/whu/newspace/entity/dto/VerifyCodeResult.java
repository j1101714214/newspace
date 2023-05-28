package edu.whu.newspace.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Newspace
 * @version 1.0
 * @description 验证码结果
 * @date 2023/5/25 15:53
 */
@Data
@AllArgsConstructor
public class VerifyCodeResult {
    // 前端验证
    String verifyCode;
    String verifyImg;
}
