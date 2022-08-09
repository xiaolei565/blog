package cn.ustc.blog.service.Impl;

import cn.ustc.blog.entity.MBlog;
import cn.ustc.blog.mapper.MBlogMapper;
import cn.ustc.blog.service.MBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-04-16
 */
@Service
public class MBlogServiceImp extends ServiceImpl<MBlogMapper, MBlog> implements MBlogService {

}
