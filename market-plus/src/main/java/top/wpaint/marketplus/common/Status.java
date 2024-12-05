package top.wpaint.marketplus.common;

import lombok.Getter;

@Getter
public enum Status {
    // 通用状态
    SUCCESS(200, "ok"),
    ERROR(400, "未知错误"),

    // 401
    USER_NOT_LOGIN(400, "你就没登陆"),

    // 注册
    VERIFY_CODE_NOT_EQ(400, "验证码不正确"),
    MAIL_NOT_SEND(400, "请先发送验证码"),
    SEND_MAIL_ERR(400, "验证码发送失败"),
    USER_EXISTS(400, "用户已经存在"),
    TWICE_PASSWD_NOT_EQ(400, "两次输入密码不匹配"),

    // 登陆
    USER_NOT_FOUND(400, "用户不存在"),
    USERNAME_OR_PASSWD_ERR(400, "用户名或者密码错误"),


    SEND_MAIL_OK(200, "发送成功，请验收"),
    REGISTER_OK(200, "注册成功"),



    USER_AUTH_TYPE_NOT_SUPPORT(400, "不支持的登陆形式"),
    OLD_PASSWORD_NOT_EQ(400, "旧密码不正确");





    private final int code;

    private final String message;

    Status(int code, String msg) {
        this.code = code;
        this.message = msg;
    }


}