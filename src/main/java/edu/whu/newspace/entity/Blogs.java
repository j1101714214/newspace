package edu.whu.newspace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import edu.whu.newspace.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tt_blogs")
@ApiModel(value="Blogs对象", description="")
public class Blogs implements Serializable {
    @TableId("id")
    private Long id;

    @TableField("title")
    private String title;

    @TableField("link")
    private String link;

    @TableField("image")
    private String image;

    @TableField("like_count")
    private Integer likeCount;

    @TableField("comment_count")
    private Integer commentCount;

    @TableField("created_date")
    private LocalDateTime createdDate;

    @TableField("user_id")
    private Long userId;

    @TableField("audit_status")
    private String auditStatus;

    @TableField("content")
    private String content;


}
