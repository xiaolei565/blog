package cn.ustc.response;

/**
 * 定义状态码
 */
public enum ResultCode implements CustomizeResultCode{
    SUCCESS(20000,"成功"),
    ERROR(1001,"失败"),
    LOGIN_SUCCESS(20001,"登录成功"),
    REGISTER_SUCCESS(2000,"用户注册成功"),
    REGISTER_FAIL(2004,"用户注册失败"),
    USER_REGISTER_FAIL(2005,"用户激活失败"),
    USER_NOT_FOUND_EXCEPTION(1005,"用户不存在"),
    ARITHMETICEXCEPTION(3004,"算数错误")
    ;


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
