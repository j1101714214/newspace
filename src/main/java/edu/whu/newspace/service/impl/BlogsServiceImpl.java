package edu.whu.newspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.newspace.constant.BlogsStatus;
import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.Message;
import edu.whu.newspace.entity.dto.BlogsPostDTO;
import edu.whu.newspace.exception.TouTiaoException;
import edu.whu.newspace.mapper.BlogsMapper;
import edu.whu.newspace.service.IBlogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.newspace.service.IFriendsService;
import edu.whu.newspace.service.IMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Service
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs> implements IBlogsService {
    @Autowired
    private BlogsMapper blogsMapper;
    @Override
    public List<Blogs> queryBlogsList(int pageNum, int pageSize) {
        return blogsMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Blogs>().orderByDesc(Blogs::getCreatedDate)
        ).getRecords();
    }

    @Override
    public List<Blogs> queryBlogsOfCurrentUser(int pageNum, int pageSize, Long userId) {
        return blogsMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Blogs>()
                        .orderByDesc(Blogs::getCreatedDate)
                        .eq(Blogs::getUserId, userId)
        ).getRecords();
    }

    @Override
    @Transactional
    public boolean postBlogs(BlogsPostDTO blogsPostDTO) {
        Blogs blogs = new Blogs();
        BeanUtils.copyProperties(blogsPostDTO, blogs);
        blogs.setCommentCount(0);
        blogs.setLikeCount(0);
        blogs.setCreatedDate(LocalDateTime.now());
        blogs.setAuditStatus(BlogsStatus.WAITING_FOR_AUDIT_CODE.getCode());
        return blogsMapper.insert(blogs) == 1;
    }

    @Override
    public boolean updateLikeCount(Long blogId, int likeCount) {
        Blogs blogs = blogsMapper.selectById(blogId);
        if(blogs == null) {
            TouTiaoException.cast("博客不存在!");
        }
        blogs.setLikeCount(likeCount);
        return blogsMapper.updateById(blogs) == 1;
    }

    @Override
    public boolean updateCommentCount(Long blogId, int commentCount) {
        Blogs blogs = blogsMapper.selectById(blogId);
        if(blogs == null) {
            TouTiaoException.cast("博客不存在!");
        }
        blogs.setCommentCount(commentCount);
        return blogsMapper.updateById(blogs) == 1;
    }

    @Override
    public boolean deleteBlogs(Long blogId) {
        // TODO:Controller层使用java securty验证身份!
        return blogsMapper.deleteById(blogId) == 1;
    }

    @Override
    public boolean updateBlogContent(Long blogId, String content) {
        Blogs blogs = blogsMapper.selectById(blogId);
        if(blogs == null) {
            TouTiaoException.cast("博库不存在");
        }
        blogs.setContent(content);
        return blogsMapper.updateById(blogs) == 1;
    }

    @Override
    public boolean updateBlogStatus(Long id, BlogsStatus status) {
        LambdaUpdateWrapper<Blogs> luw = new LambdaUpdateWrapper<>();
        luw.eq(Blogs::getId, id).set(Blogs::getAuditStatus, status.getCode());
        return blogsMapper.update(null, luw) == 1;
    }

    @Override
    public Blogs queryBlogById(Long id) {
        return blogsMapper.selectById(id);
    }

    @Override
    public List<Blogs> queryBlogsByStatus(int pageSize, BlogsStatus status) {
        LambdaQueryWrapper<Blogs> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Blogs::getAuditStatus, status.getCode());
        return blogsMapper.selectPage(
                new Page<>(1, pageSize),
                lqw
        ).getRecords();
    }
}
