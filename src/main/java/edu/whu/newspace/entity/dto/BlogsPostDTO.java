package edu.whu.newspace.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import edu.whu.newspace.exception.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Newspace
 * @version 1.0
 * @description 上传博客用DTO
 * @date 2023/5/24 16:36
 */
@Data
public class BlogsPostDTO {
    @NotEmpty(message = "标题不可为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String title;
    private String link;
    @NotEmpty(message = "不可为不存在的用户发表博客", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Long userId;
    @Size(min = 10, message = "内容不可少于10个字")
    private String content;
}
