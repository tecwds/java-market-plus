package top.wpaint.marketplus.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 用户信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    /**
     * 用户 ID
     */
    private BigInteger userId;

    /**
     * 用户名（邮箱）
     */
    private String email;

    /**
     * 用户昵称
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