package cn.ustc.blog.entity;

import cn.ustc.blog.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("sys_user_role")
@ApiModel(value = "SysUserRole对象", description = "")
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;


}
