package cn.ustc.handler;

import cn.ustc.response.Result;
import cn.ustc.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-02 16:12
 * @Description: 全局异常处理
 */
//@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，没有指定异常类型，不管什么异常都是可以捕获的
     * 注意是以json返回，需要ResponseBody注解
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //json
    public Result error(ArithmeticException e){
        e.printStackTrace();
        //打印测试
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ARITHMETICEXCEPTION.getCode())
                .message(ResultCode.ARITHMETICEXCEPTION.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode()).message(e.getErrMsg());
    }

}
