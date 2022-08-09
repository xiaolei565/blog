package cn.ustc.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String userpwd;

//    用以后面和uuid加密密码
    private String salt;

    private String email;

    @ApiModelProperty("0-普通用户; 1-超级管理员; 2-版主;")
    private Integer type;

    @ApiModelProperty("0-未激活; 1-已激活;")
    private Integer status;

    private String activationCode;

    private String headerUrl;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
