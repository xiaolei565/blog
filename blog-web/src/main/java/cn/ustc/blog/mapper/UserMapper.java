package cn.ustc.blog.mapper;

import cn.ustc.blog.entity.User;
import cn.ustc.response.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    Result updateById(int id);

    List<String> getRoleCodeListByUserName(String username);


}
