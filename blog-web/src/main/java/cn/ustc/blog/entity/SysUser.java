package cn.ustc.blog.entity;

import cn.ustc.blog.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-04-11
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;


}
