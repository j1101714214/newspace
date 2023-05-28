package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.dto.BlogsPostDTO;
import edu.whu.newspace.service.IBlogsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogsServiceImplTest {
    @Autowired
    private IBlogsService blogsService;

    private BlogsPostDTO blog;
    @BeforeEach
    public void init() {
        blog = new BlogsPostDTO();
        blog.setTitle("测试4");
        blog.setContent("测试4");
        blog.setUserId(1660947736294543367L);
    }

    @AfterEach
    public void destroy() {
        blog = null;
    }

    @Test
    @Rollback
    void queryBlogsList() {
        List<Blogs> blogs = blogsService.queryBlogsList(1, 3);
        assertNotNull(blogs);
        assertEquals(3, blogs.size());
        assertEquals("审核测试3", blogs.get(0).getContent());
    }

    @Test
    @Rollback
    void postBlogs() {
        blogsService.postBlogs(blog);
        // 查询全部查看是否包含插入的博客, 验证时数据库无重复数据
        List<Blogs> blogs = blogsService.queryBlogsList(1, 5);
        List<Blogs> blogsList = blogs.stream().filter(blogs1 -> blogs1.getContent().equals(blog.getContent())).collect(Collectors.toList());
        assertNotNull(blogsList);
        assertEquals(1, blogsList.size());

        assertEquals(blog.getTitle(), blogsList.get(0).getTitle());
    }

    @Test
    void deleteBlogs() {
    }
}