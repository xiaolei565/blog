package cn.ustc.blog.mapper;

import cn.ustc.blog.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaolei565
 * @since 2022-04-11
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /** 通过用户id找权限*/
    List<String> getRolesByUserId(Long userId);

}
