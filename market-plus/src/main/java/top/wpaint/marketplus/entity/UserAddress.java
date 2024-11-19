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
 * @since 2024-11-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "wb_user_address")
public class UserAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private BigInteger id;

    /**
     * 地址 ID
     */
    private BigInteger addressId;

    /**
     * 关联用户ID
     */
    private BigInteger userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String country;

    /**
     * 详细地址
     */
    private String detail;

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