package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswdDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 旧密码
     */
    private String oldPasswd;

    /**
     * 新密码
     */
    private String newPasswd;

    /**
     * 确认密码
     */
    private String confirmPasswd;
}
