package edu.whu.newspace.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.dto.BlogsPostDTO;
import edu.whu.newspace.service.IBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    private IBlogsService blogsService;
    @GetMapping("/all")
    public ResponseEntity<List<Blogs>> getAllBlog(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(blogsService.queryBlogsList(pageNum, pageSize));
    }

    @PostMapping("/post")
    public ResponseEntity<String> postBlogs(@RequestBody BlogsPostDTO blogsPostDTO) {
        boolean ret = blogsService.postBlogs(blogsPostDTO);
        if(ret) {
            return ResponseEntity.ok("上传博客成功");
        } else {
            return ResponseEntity.badRequest().body("上传博客失败");
        }
    }
}

