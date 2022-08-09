package cn.ustc.object.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.object.dto
 * @Author: leixue
 * @CreateTime: 2022-04-03 17:36
 * @Description: User传输对象
 */
@Data
public class UserDto implements Serializable {

    private String username;

    private String userpwd;

    private String code;

    private String token;
}
