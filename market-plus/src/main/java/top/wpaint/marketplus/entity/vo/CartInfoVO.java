package top.wpaint.marketplus.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.RelationOneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartInfoVO implements Serializable {

    /**
     * 商品 ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger goodsId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品图像
     */
    private String image;

    /**
     * 价格
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger price;

    /**
     * 数量（来自 Cart 购物车）
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long count;
}
