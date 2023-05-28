package edu.whu.newspace.controller;


import edu.whu.newspace.entity.dto.CommentPostDTO;
import edu.whu.newspace.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping("post/{blogId}")
    public ResponseEntity<String> postComment(@PathVariable("blogId") Long blogId, @RequestBody CommentPostDTO commentPostDTO) {
        Boolean ret = commentService.addCommentToBlogs(blogId, commentPostDTO);
        if(ret) {
            return ResponseEntity.ok("上传评论成功");
        }else {
            return ResponseEntity.badRequest().body("上传评论失败");
        }
    }

}

