package edu.whu.newspace.entity.dto;

import lombok.Data;

/**
 * @author Newspace
 * @version 1.0
 * @description 用户登录DTO
 * @date 2023/5/26 16:08
 */
@Data
public class UserLoginDTO {
    private String username;
    private String password;
}
