package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;  
    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private Integer verCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 二次密码
     */
    private String rePassword;
}