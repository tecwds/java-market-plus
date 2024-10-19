package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import top.wpaint.marketplus.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serial;

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
@Table(value = "wb_user")
public class User extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

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

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreated;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 是否删除（逻辑删除）
     */
    private Integer isDeleted;

    /**
     * 是否启用（激活）
     */
    private Integer isEnable;

}
