package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
 * @since 2024-11-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "wb_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
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
    private String email;

    /**
     * 用户昵称（随意）
     */
    private String username;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 性别
     */
    private Long gender;

    /**
     * （默认）登陆类型
     */
    private Integer authType;

    /**
     * 权限类型
     */
    private String roleName;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime gmtCreated;

    /**
     * 修改时间
     */
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime gmtModified;

    /**
     * 是否删除（逻辑删除）
     */
    @Column(onInsertValue = "0", isLogicDelete = true)
    private Integer isDeleted;

    /**
     * 是否启用（激活）
     */
    @Column(onInsertValue = "0")
    private Integer isEnable;

}
