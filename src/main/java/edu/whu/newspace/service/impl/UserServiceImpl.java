package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.User;
import edu.whu.newspace.mapper.UserMapper;
import edu.whu.newspace.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
