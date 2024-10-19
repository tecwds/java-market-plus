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
@Table("wb_user_auth")
public class UserAuth extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联认证 ID
     */
    private BigInteger authId;

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

}
