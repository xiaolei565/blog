package cn.ustc.system.mapper;

import cn.ustc.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.system.mapper
 * @Author: leixue
 * @CreateTime: 2022-03-27 14:50
 * @Description: user dao
 */
@Mapper
public interface UserMapper {
    /**根据id查找用户*/
//    @Select("select * from user where id = #{id}")
    User getUserById(int id);
}
