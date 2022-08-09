package cn.ustc.blog.controller;


//import cn.hutool.db.Page;
import cn.hutool.core.bean.BeanUtil;
import cn.ustc.blog.entity.MBlog;
import cn.ustc.blog.service.MBlogService;
import cn.ustc.response.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.util.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaolei565
 * @since 2022-04-16
 */
@RestController
public class MBlogController {

    @Autowired
    MBlogService mBlogService;

    @GetMapping("/blogs")
    public Result blogs(@RequestParam(defaultValue = "1") Integer currentPage) {
        //这里千万别引入成了hutools的分页
        Page page = new Page(currentPage, 5);
        IPage pageData = mBlogService.page(new Page(currentPage, 5), new QueryWrapper<MBlog>().orderByDesc("created"));
        return Result.ok().code(20000).message("查询成功").data("blogs",pageData);
    }
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        MBlog blog = mBlogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.ok().code(20000).message("返回博客详细信息").data("blog",blog);
    }

    /**
     * 编辑和新建需要admin权限
     * 都放到一个接口里面就好了
     */
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody MBlog mBlog) {
//        System.out.println(mBlog.toString());
        MBlog temp = null;
        if(mBlog.getId() != null) {
            temp = mBlogService.getById(mBlog.getId());
            //确定这个的博文的用户是不是和登录的用户是一个，这里怎么写，需要好好考虑一下
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
        } else {
            temp = new MBlog();
//            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(mBlog, temp, "id", "userId", "created", "statu");
        mBlogService.saveOrUpdate(temp);
//        return Result.succ("操作成功", null);
        return Result.ok();
    }

}

