package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswdDTO {

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
