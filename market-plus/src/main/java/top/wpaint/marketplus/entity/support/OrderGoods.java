package top.wpaint.marketplus.entity.support;

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
public class OrderGoods implements Serializable {
    private BigInteger goodsId;
    private Long count;
}
