package edu.whu.newspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.Friends;
import edu.whu.newspace.mapper.BlogsMapper;
import edu.whu.newspace.mapper.FriendsMapper;
import edu.whu.newspace.service.IBlogsService;
import edu.whu.newspace.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements IFriendsService {
    @Autowired
    private FriendsMapper friendsMapper;
    @Override
    public boolean follow(Long follower, Long followee) {
        Friends friends = new Friends(follower, followee);
        return friendsMapper.insert(friends) == 1;
    }

    @Override
    public boolean unfollow(Long follower, Long followee) {
        LambdaQueryWrapper<Friends> lqw = new LambdaQueryWrapper<>();
        lqw.and(wq ->  wq.eq(Friends::getUserId, follower).eq(Friends::getFriendId, followee))
                .or(wq -> wq.eq(Friends::getUserId, follower).eq(Friends::getFriendId, followee));
        System.out.println(lqw.getTargetSql());
        return friendsMapper.delete(lqw) > 0;
    }

    @Override
    public List<Long> getFollowers(Long userId, int pageNum, int pageSize) {
        return friendsMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Friends>().eq(Friends::getUserId, userId)
        ).getRecords().stream().map(Friends::getFriendId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getFollowees(Long userId, int pageNum, int pageSize) {
        return friendsMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Friends>().eq(Friends::getFriendId, userId)
        ).getRecords().stream().map(Friends::getUserId).collect(Collectors.toList());
    }

}
