package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 商品所属商户ID
     */
    private BigInteger storeId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 商品价格
     */
    private BigInteger price;

    /**
     * 商品数量
     */
    private Integer quantity;
}
