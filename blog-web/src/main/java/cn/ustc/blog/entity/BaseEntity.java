package cn.ustc.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.blog.entity
 * @Author: leixue
 * @CreateTime: 2022-04-11 14:47
 * @Description: 实体类基类
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Integer statu;
}
