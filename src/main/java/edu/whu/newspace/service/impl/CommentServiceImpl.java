package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Comment;
import edu.whu.newspace.mapper.CommentMapper;
import edu.whu.newspace.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
