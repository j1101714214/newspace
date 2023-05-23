package edu.whu.newspace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tt_user")
@ApiModel(value="User对象", description="用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("head_url")
    private String headUrl;


}
