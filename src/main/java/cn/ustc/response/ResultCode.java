package cn.ustc.response;

/**
 * 定义状态码
 */
public enum ResultCode implements CustomizeResultCode{
    SUCCESS(1000,"成功"),
    ERROR(1001,"失败")
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
