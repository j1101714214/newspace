package edu.whu.newspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.newspace.entity.Comment;
import edu.whu.newspace.entity.Friends;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
public interface IFriendsService extends IService<Friends> {
    /**
     * 关注操作
     * @param follower  关注者
     * @param followee  被关注着
     * @return          操作结果
     */
    boolean follow(Long follower, Long followee);

    /**
     * 取关操作
     * @param follower  关注者
     * @param followee  被关注着
     * @return          操作结果
     */
    boolean unfollow(Long follower, Long followee);

    /**
     * 获取当前用户关注的用户
     * @param userId    当前用户id
     * @param pageSize  当前页码
     * @param pageNum   页面大小
     * @return          关注者id列表
     */
    List<Long> getFollowers(Long userId, int pageNum, int pageSize);

    /**
     * 获取关注当前用户的用户
     * @param userId    用户id
     * @param pageSize  当前页码
     * @param pageNum   页面大小
     * @return          被关注着id列表
     */
    List<Long> getFollowees(Long userId, int pageNum, int pageSize);
}
