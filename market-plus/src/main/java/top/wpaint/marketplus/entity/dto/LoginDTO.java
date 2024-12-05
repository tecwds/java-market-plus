package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 登陆账号
     */
    private String email;

    /**
     * 用户名，也可以登陆，邮箱优先
     */
    private String username;

    /**
     * 登陆密码
     */
    private String password;
}