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
 * @since 2024-11-19
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
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

    /**
     * 订单 ID
     */
    private BigInteger orderId;

    private BigInteger userId;

    /**
     * 购买来源店铺ID
     */
    private BigInteger storeId;

    /**
     * 购买的商品ID
     */
    private BigInteger productId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 成交单价
     */
    private BigInteger price;

    /**
     * 成交数量
     */
    private Integer quantity;

    /**
     * 总成交价格
     */
    private BigInteger totalPrice;

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
