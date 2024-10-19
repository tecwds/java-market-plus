package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import top.wpaint.marketplus.entity.BaseEntity;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 *  实体类。
 *
 * @author tecwds
 * @since 2024-10-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("wb_user")
public class User extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private BigInteger userId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户名（唯一）
     */
    private String username;

    /**
     * 用户昵称（随意）
     */
    private String nickname;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 性别
     */
    private Long gender;

    /**
     * 认证信息
     */
    private String authorization;

    /**
     * （默认）登陆类型
     */
    private Integer authType;

    /**
     * 权限类型
     */
    private BigInteger rule;

}
