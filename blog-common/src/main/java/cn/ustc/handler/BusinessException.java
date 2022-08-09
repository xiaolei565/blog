package cn.ustc.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-02 17:29
 * @Description: 业务异常类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "错误信息")
    private String errMsg;
}
