package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 个性签名
     */
    private String signature;
}