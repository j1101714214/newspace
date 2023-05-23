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
@TableName("tt_comment")
@ApiModel(value="Comment对象", description="评论信息")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private Integer userId;

    @TableField("entity_id")
    private Integer entityId;

    @TableField("entity_type")
    private Integer entityType;

    @TableField("created_date")
    private Date createdDate;

    @TableField("status")
    private Integer status;


}
