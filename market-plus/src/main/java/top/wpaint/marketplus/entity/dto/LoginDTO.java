package top.wpaint.marketplus.entity.dto;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 登陆账号（邮箱、第三方认证）
     */
    private String accessKey;

    /**
     * 登陆密码
     */
    private String secretKey;

    /**
     * 登陆类型
     * 具体参考 {@link top.wpaint.marketplus.common.constant.AuthConst}
     */
    private Integer type;

}