package edu.whu.newspace.service;

import edu.whu.newspace.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.newspace.entity.dto.UserRegisterDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
public interface IUserService extends IService<User> {
    /**
     * 添加新用户
     * @param userRegisterDTO   user实体对象
     * @return                  添加操作结果
     */
    boolean addUser(UserRegisterDTO userRegisterDTO);

    /**
     * 根据用户名查询用户
     * @param username  用户名
     * @return          查询结果
     */
    User queryUserByName(String username);

    /**
     * 根据id查询用户接口
     * @param id    用户id
     * @return      id对应地用户对象
     */
    User queryUserById(Long id);

    /**
     * 更新用户信息
     * @param user  用户信息
     * @return      更新结果
     */
    boolean updateUserInfo(User user);

    /**
     * 删除用户信息
     * @param id    用户id
     * @return      删除操作操作结果
     */
    boolean deleteUserById(Long id);

    /**
     * 分页查询用户列表
     * @param pageSize  页面大小
     * @param pageNum   页面数
     * @return          用户列表
     */
    List<User> queryUserList(int pageSize, int pageNum);

    /**
     * 为用户添加权限
     * @param userId    用户id
     * @param roles     角色
     * @return          操作结果
     */
    boolean addRoles(Long userId, String roles);

    /**
     * 删除权限
     * @param userId    用户id
     * @param roles     待删除权限
     * @return          操作结果
     */
    boolean deleteRoles(Long userId, String roles);
}
