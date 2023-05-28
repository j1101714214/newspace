package edu.whu.newspace.service;

import edu.whu.newspace.constant.BlogsStatus;
import edu.whu.newspace.entity.Blogs;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.newspace.entity.dto.BlogsPostDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
public interface IBlogsService extends IService<Blogs> {
    /**
     * 分页查询所有博客
     * @param pageNum   当前页号
     * @param pageSize  页面大小
     * @return          博客列表
     */
    List<Blogs> queryBlogsList(int pageNum, int pageSize);

    /**
     * 查询当前用户的博客数量
     * @param pageNum   当前页号
     * @param pageSize  页面大小
     * @param userId    当前用户id
     * @return          博客列表
     */
    List<Blogs> queryBlogsOfCurrentUser(int pageNum, int pageSize, Long userId);

    /**
     * 发布博客
     * @param blogsPostDTO 博客信息
     * @return              插入后的id值
     */
    boolean postBlogs(BlogsPostDTO blogsPostDTO);

    /**
     * 更新点赞次数
     * @param blogId    博客id
     * @param likeCount 点赞数
     * @return          操作结果
     */
    boolean updateLikeCount(Long blogId, int likeCount);

    /**
     * 更新评论次数
     * @param blogId        评论结果
     * @param commentCount  评论数
     * @return              操作结果
     */
    boolean updateCommentCount(Long blogId, int commentCount);

    /**
     * 删除博客
     * @param blogId    博客ID
     * @return          操作结果
     */
    boolean deleteBlogs(Long blogId);

    /**
     * 更新文章内容
     * @param blogId
     * @param content
     * @return
     */
    boolean updateBlogContent(Long blogId, String content);


    /**
     * 更新博客状态
     * @param id        博客id
     * @param status    博客状态
     * @return          操作结果
     */
    boolean updateBlogStatus(Long id, BlogsStatus status);

    /**
     * 通过id查询文件
     * @param id    博客id
     * @return      博客对象
     */
    Blogs queryBlogById(Long id);

    /**
     * 查询指定状态的博客
     * @param pageSize  条目数
     * @param status    博客状态
     * @return  博客列表(不分页)
     */
    List<Blogs> queryBlogsByStatus(int pageSize, BlogsStatus status);
}
