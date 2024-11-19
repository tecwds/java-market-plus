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
@Table(value = "wb_product_tag")
public class ProductTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

    /**
     * 关联商品 ID
     */
    private BigInteger productId;

    /**
     * 关联标签ID
     */
    private BigInteger tagId;

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
