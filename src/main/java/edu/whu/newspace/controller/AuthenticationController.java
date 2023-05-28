package edu.whu.newspace.controller;

import com.sun.org.apache.regexp.internal.RE;
import edu.whu.newspace.entity.dto.UserRegisterDTO;
import edu.whu.newspace.exception.ValidationGroups;
import edu.whu.newspace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Newspace
 * @version 1.0
 * @description 登录注册相关模块
 * @date 2023/5/25 15:59
 */
@Controller("auth")
public class AuthenticationController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    private ResponseEntity<String> register(@RequestBody @Validated(ValidationGroups.Insert.class)UserRegisterDTO userRegisterDTO) {
        boolean ret = userService.addUser(userRegisterDTO);
        if(ret) {
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.badRequest().body("注册失败");
        }
    }

    // TODO: Java Security实现登录而非普通的验证
}
