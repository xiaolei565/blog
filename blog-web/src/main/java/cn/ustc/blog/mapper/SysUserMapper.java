package cn.ustc.blog.mapper;

import cn.ustc.blog.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Long> getNavMenuIds(Long userId);

    List<SysUser> listByMenuId(Long menuId);
}
