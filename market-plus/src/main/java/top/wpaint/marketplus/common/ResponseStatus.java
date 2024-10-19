package top.wpaint.marketplus.common;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS(200, "成功"),
    ERROR(400, "未知错误"),
    USER_NOT_FOUND(400, "用户不存在"),
    USER_NOT_ENABLE(400, "用户未激活"),
    TWICE_PASSWD_NOT_EQ(400, "两次输入密码不匹配");



    private final int code;

    private final String message;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.message = msg;
    }


}