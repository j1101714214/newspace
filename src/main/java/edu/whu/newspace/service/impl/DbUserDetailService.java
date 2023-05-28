package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.User;
import edu.whu.newspace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Newspace
 * @version 1.0
 * @description 自定义用户详情管理
 * @date 2023/5/26 15:29
 */
@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserByName(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户: " + username + " 不存在");
        }
        return org.springframework.security.core.userdetails.User
                .builder().username(username)
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(user.getRoles().split(",")).build();
    }
}
