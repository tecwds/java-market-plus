package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.wpaint.marketplus.entity.support.OrderGoods;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {
    private BigInteger id;

    /**
     * 关联用户 ID
     */
    private String userId;

    /**
     * 商品信息
     */
    private List<OrderGoods> goods;
}
