package top.wpaint.marketplus.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class OrderVO implements Serializable {
    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger id;
    /**
     * 关联商品
     */
    private List<GoodsVO> goods;
}
