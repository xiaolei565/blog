package cn.ustc.handler;

import cn.hutool.core.util.StrUtil;
import cn.ustc.response.Const;
import cn.ustc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: leixue
 * @CreateTime: 2022-04-07 16:16
 * @Description: 验证码过滤器,在用户和密码拦截器前做这个操作
 * 继承一次性校验请求过滤器
 */
@Component
public class VerifyImgFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;
    /**失败处理器**/
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //只拦截登录即可，需要对登录url进行判断
        String url = request.getRequestURI();
        if ("/login".equals(url) && "POST".equals(request.getMethod())){
            //校验验证码
            try{
                validate(request);
                //过滤器继续往下走，这里逻辑有问题吧

            }catch (VerifyImgException e){
                e.printStackTrace();
                //不正确，跳转到认证失败处理器
//                System.out.println("这里有验证码问题");
                myAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);

                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    /**校验逻辑*/
    private void validate(HttpServletRequest request) {
        // 用户输入的验证码code
        String code = request.getParameter("code");
        // key为之前服务器传给客户端的token，根据key来查找redis中的code，判断是否相等
        String key = request.getParameter("token");
        if(StrUtil.isBlank(code) || StrUtil.isBlank(key)){
            throw new VerifyImgException("验证码不可为空");
            //按说这里就得终止了，不允许继续进行校验
        }
        if(!redisUtil.hget(Const.VERIFYIMG,key).equals(code)){
            throw new VerifyImgException("验证码不正确");
        }
        //去掉，一次性使用
        redisUtil.hdel(Const.VERIFYIMG,key);
    }
}
