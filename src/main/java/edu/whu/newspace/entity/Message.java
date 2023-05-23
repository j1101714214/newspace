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
@TableName("tt_message")
@ApiModel(value="Message对象", description="消息推送")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("from_id")
    private Integer fromId;

    @TableField("to_id")
    private Integer toId;

    @TableField("content")
    private String content;

    @TableField("created_date")
    private Date createdDate;

    @TableField("has_read")
    private Integer hasRead;

    @TableField("conversation_id")
    private String conversationId;


}
