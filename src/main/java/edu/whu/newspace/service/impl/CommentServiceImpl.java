package edu.whu.newspace.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.newspace.constant.CommentStatus;
import edu.whu.newspace.entity.Comment;
import edu.whu.newspace.entity.dto.CommentPostDTO;
import edu.whu.newspace.exception.TouTiaoException;
import edu.whu.newspace.mapper.BlogsMapper;
import edu.whu.newspace.mapper.CommentMapper;
import edu.whu.newspace.service.IBlogsService;
import edu.whu.newspace.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private IBlogsService blogsService;
    @Override
    public Boolean addCommentToBlogs(Long blogId, CommentPostDTO commentPostDTO) {
        if(blogsService.queryBlogById(blogId) == null) {
            TouTiaoException.cast("博客不存在");
        }
        Comment comment = new Comment();
        comment.setContent(commentPostDTO.getContent());
        comment.setUserId(commentPostDTO.getUserId());
        comment.setEntityId(blogId);
        comment.setStatus(CommentStatus.WAITING_FOR_AUDIT_CODE.getCode());
        comment.setCreatedDate(LocalDateTime.now());
        return commentMapper.insert(comment) == 1;
    }

    @Override
    public List<Comment> queryCommentPages(int pageNum, int pageSize) {
        return commentMapper.selectPage(new Page<>(pageNum, pageSize), null).getRecords();
    }
}
