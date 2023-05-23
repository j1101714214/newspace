package edu.whu.newspace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="Blogs对象", description="博客信息")
public class Blogs implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Date createdDate;

    @TableField("user_id")
    private Integer userId;


}
