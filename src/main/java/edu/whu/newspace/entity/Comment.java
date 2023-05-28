package edu.whu.newspace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {
    @TableId("id")
    private Long id;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private Long userId;

    @TableField("entity_id")
    private Long entityId;

    @TableField("entity_type")
    private Integer entityType;

    @TableField("created_date")
    private LocalDateTime createdDate;

    @TableField("status")
    private String status;


}
