package cn.ustc.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum StatusEnum implements CustomizeResultCode{
    ACTIVED(1,"已激活"),
    ;
    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
