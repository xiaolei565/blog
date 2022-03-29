package cn.ustc.blog.mapper;

import cn.ustc.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
