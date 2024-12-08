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
 * @since 2024-12-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "wb_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表的 ID 主键
     */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private BigInteger id;

    /**
     * 订单 ID
     */
    private BigInteger orderId;

    /**
     * 关联用户 ID
     */
    private BigInteger userId;

    /**
     * 关联商品ID
     */
    private BigInteger goodsId;

    /**
     * 单价
     */
    private BigInteger price;

    /**
     * 总价
     */
    private BigInteger total;

    /**
     * 数量
     */
    private Long count;

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
