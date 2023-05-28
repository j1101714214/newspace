package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.Comment;
import edu.whu.newspace.entity.dto.CommentPostDTO;
import edu.whu.newspace.exception.TouTiaoException;
import edu.whu.newspace.service.ICommentService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private ICommentService commentService;

    private CommentPostDTO commentPostDTO;
    @BeforeEach
    public void init() {
        commentPostDTO = new CommentPostDTO();
        commentPostDTO.setContent("评论1");
        commentPostDTO.setUserId(1660947736294543368L);
    }
    @AfterEach
    public void  destroy() {
        commentPostDTO = null;
    }
    @Test
    @Rollback
    void addCommentToBlogs() {
        commentService.addCommentToBlogs(1L, commentPostDTO);
        // 查询全部查看是否包含插入的评论, 验证时数据库无重复数据
        List<Comment> comments = commentService.queryCommentPages(1, 5);
        List<Comment> commentList = comments.stream().filter(comment -> comment.getContent().equals(commentPostDTO.getContent())).collect(Collectors.toList());
        assertNotNull(commentList);
        assertEquals(1, commentList.size());

        assertEquals(commentPostDTO.getContent(), commentList.get(0).getContent());

        assertThrows(TouTiaoException.class, () -> commentService.addCommentToBlogs(10L, commentPostDTO));
    }
}