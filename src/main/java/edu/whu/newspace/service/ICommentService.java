package edu.whu.newspace.service;

import edu.whu.newspace.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.newspace.entity.dto.CommentPostDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 上传评论
     * @param blogId            博客id
     * @param commentPostDTO    评论
     * @return                  操作结果
     */
    Boolean addCommentToBlogs(Long blogId, CommentPostDTO commentPostDTO);

    /**
     * 分页查询评论信息
     * @param pageNum   当前页码
     * @param pageSize  页大小
     * @return          评论列表
     */
    List<Comment> queryCommentPages(int pageNum, int pageSize);
}
