package top.wpaint.marketplus.entity.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
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