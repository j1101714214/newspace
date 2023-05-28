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
@TableName("tt_message")
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {
    @TableId("id")
    private Long id;

    @TableField("from_id")
    @NotEmpty(message = "发件人不能为空")
    private Long fromId;

    @TableField("to_id")
    @NotEmpty(message = "收件人不能为空")
    private Long toId;

    @TableField("content")
    private String content;

    @TableField("created_date")
    private LocalDateTime createdDate;

    @TableField("has_read")
    private Integer hasRead;

    @TableField("conversation_id")
    private String conversationId;


}
