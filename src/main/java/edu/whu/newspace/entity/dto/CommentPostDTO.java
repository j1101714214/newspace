package edu.whu.newspace.entity.dto;

import lombok.Data;

/**
 * @author Newspace
 * @version 1.0
 * @description TODO
 * @date 2023/5/28 19:01
 */
@Data
public class CommentPostDTO {
    private String content;
    private Long userId;
}
