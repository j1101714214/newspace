package edu.whu.newspace.entity.dto;

import edu.whu.newspace.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Newspace
 * @version 1.0
 * @description 用户注册用DTO
 * @date 2023/5/23 11:06
 */
@Data
@ApiModel(value="UserRegisterDTO对象", description="用户注册信息")
public class UserRegisterDTO {
    @NotEmpty(message = "注册用户名不可为空", groups = ValidationGroups.Insert.class)
    private String name;
    @NotEmpty(message = "注册密码不可为空", groups = ValidationGroups.Insert.class)
    @Size(min = 8, max = 16, message = "密码长度为8-16位")
    private String password;
}
