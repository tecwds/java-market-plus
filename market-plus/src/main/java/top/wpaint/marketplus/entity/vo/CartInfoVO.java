package top.wpaint.marketplus.entity.vo;

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
    private BigInteger price;

    /**
     * 数量（来自 Cart 购物车）
     */
    private Long count;
}
