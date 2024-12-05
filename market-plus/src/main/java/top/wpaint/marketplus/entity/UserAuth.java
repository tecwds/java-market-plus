package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;

/**
 *  实体类。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "wb_user_auth")
public class UserAuth implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

    /**
     * 关联用户 ID
     */
    private BigInteger userId;

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

    /**
     * 登录时的用户名、邮箱或者第三方Token
     */
    private String accessKey;

    /**
     * 密码，如果存在
     */
    private String secretKey;

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
