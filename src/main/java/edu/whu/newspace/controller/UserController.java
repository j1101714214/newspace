package edu.whu.newspace.controller;


import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.whu.newspace.entity.User;
import edu.whu.newspace.entity.dto.UserRegisterDTO;
import edu.whu.newspace.exception.ValidationGroups;
import edu.whu.newspace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("list")
    public ResponseEntity<List<User>> list(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(userService.queryUserList(pageSize, pageNum));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.queryUserById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUserInfo(@PathVariable("id") Long id, @RequestBody User user) {
        if(user.getId() == null) {
            user.setId(id);
        }
        boolean ret = userService.updateUserInfo(user);
        if(ret) {
            return ResponseEntity.ok("更新信息成功");
        } else {
            return ResponseEntity.badRequest().body("更新信息失败");
        }
    }

    @DeleteMapping("delete/{username}")
    @PreAuthorize("hasRole('ROLE_admin') or #username == authentication.name")
    public ResponseEntity<String> deleteUserInfo(@PathVariable("username") String username, @RequestParam("id") Long id) {
        boolean ret = userService.deleteUserById(id);
        if(ret) {
            return ResponseEntity.ok("删除信息成功");
        } else {
            return ResponseEntity.badRequest().body("删除信息失败");
        }
    }

    @PostMapping("register")
    private ResponseEntity<String> registerUsers(@RequestBody @Validated(ValidationGroups.Insert.class) UserRegisterDTO userRegisterDTO) {
        boolean ret = userService.addUser(userRegisterDTO);
        if(ret) {
            return ResponseEntity.ok("注册成功:)");
        } else {
            return ResponseEntity.badRequest().body("注册失败:(");
        }
    }
}

