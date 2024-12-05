package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Column;
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
@Table(value = "wb_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表的 ID 主键
     */
    private BigInteger id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 签名
     */
    private String signature;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime gmtCreated;

    /**
     * 更新日期
     */
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime gmtModified;

    /**
     * 逻辑删除
     */
    @Column(onInsertValue = "0", isLogicDelete = true)
    private Integer isDeleted;

    /**
     * 是否启用
     */
    private Integer isEnabled;

}
