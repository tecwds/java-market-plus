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
@Table(value = "wb_user_auth")
public class UserAuth extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

    /**
     * 关联认证 ID
     */
    private BigInteger authId;

    /**
     * 关联用户 ID
     */
    private BigInteger userId;

    /**
     * 账号（用户名/邮箱）
     */
    private String userName;

    /**
     * 认证名称
     */
    private String authName;

    /**
     * 认证类型
     */
    private Integer authType;

    /**
     * 认证描述
     */
    private String description;

    private String accessKey;

    private String secretKey;

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
