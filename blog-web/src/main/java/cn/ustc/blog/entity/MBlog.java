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
 * @since 2022-04-16
 */
@Getter
@Setter
@TableName("m_blog")
@ApiModel(value = "MBlog对象", description = "")
public class MBlog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String title;

    private String description;

    private String content;

    private Integer status;


}
