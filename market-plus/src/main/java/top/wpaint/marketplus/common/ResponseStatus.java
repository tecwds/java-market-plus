package top.wpaint.marketplus.common;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS(200, "成功"),
    SEND_MAIL_OK(200, "发送成功，请验收"),
    REGISTER_OK(200, "注册成功"),
    ERROR(400, "未知错误"),
    USER_NOT_LOGIN(400, "你就没登陆"),
    USER_NOT_FOUND(400, "用户不存在"),
    USER_EXISTS(400, "用户已经存在"),
    USER_NOT_ENABLE(400, "用户未激活"),
    USER_AUTH_TYPE_NOT_SUPORRT(400, "不支持的登陆形式"),
    USERNAME_OR_PASSWD_ERR(400, "用户名或者密码错误"),
    MAIL_NOT_SEND(400, "请先发送验证码"),
    VERIFY_CODE_NOT_EQ(400, "验证码不正确"),
    TWICE_PASSWD_NOT_EQ(400, "两次输入密码不匹配");



    private final int code;

    private final String message;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.message = msg;
    }


}