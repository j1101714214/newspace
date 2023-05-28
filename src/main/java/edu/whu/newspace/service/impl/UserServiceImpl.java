package edu.whu.newspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import edu.whu.newspace.constant.UserConstant;
import edu.whu.newspace.entity.User;
import edu.whu.newspace.entity.dto.UserRegisterDTO;
import edu.whu.newspace.exception.TouTiaoException;
import edu.whu.newspace.mapper.UserMapper;
import edu.whu.newspace.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public boolean addUser(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getName();
        String passwd = userRegisterDTO.getPassword();
        // 合法性校验
        if(queryUserByName(username) != null) {
            TouTiaoException.cast("用户已存在");
        }
        if(Strings.isNullOrEmpty(passwd)) {
            TouTiaoException.cast("密码为空");
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setHeadUrl(UserConstant.DEFAULT_PIC);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDescription(UserConstant.DEFAULT_DESCRIPTION);
        user.setRoles(UserConstant.USER_ROLE);
        return userMapper.insert(user) == 1;
    }

    @Override
    public User queryUserByName(String username) {
        if(Strings.isNullOrEmpty(username)) {
            TouTiaoException.cast("用户名为空");
        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getName, username);
        return userMapper.selectOne(lqw);
    }

    @Override
    public User queryUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional
    public boolean updateUserInfo(User user) {
        if(user == null) {
            TouTiaoException.cast("传入空用户信息");
        }
        Long id = user.getId();
        User oldUser = queryUserById(id);
        if(oldUser == null) {
            TouTiaoException.cast("为不存在的用户修改信息");
        }
        BeanUtils.copyProperties(user, oldUser);
        oldUser.setId(id);
        return userMapper.updateById(oldUser) == 1;
    }

    @Override
    @Transactional
    public boolean deleteUserById(Long id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public List<User> queryUserList(int pageSize, int pageNum) {
        if(pageSize <= 0 || pageNum < 0) {
            TouTiaoException.cast("非法页号和非法页面大小");
        }
        if(pageNum > 200) {
            TouTiaoException.cast("查询列表过长");
        }
        return userMapper.selectPage(new Page<>(pageNum, pageSize), null).getRecords();
    }

    @Override
    public boolean addRoles(Long userId, String roles) {
        User user = userMapper.selectById(userId);
        user.setRoles(user.getRoles()+","+roles);
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean deleteRoles(Long userId, String roles) {
        User user = userMapper.selectById(userId);
        List<String> userRoles = Arrays.stream(user.getRoles().split(",")).collect(Collectors.toList());
        String[] roleList = roles.split(",");
        for (String role : roleList) {
            userRoles.remove(role);
        }
        user.setRoles(String.join(",", userRoles));
        return userMapper.updateById(user) == 1;
    }
}
