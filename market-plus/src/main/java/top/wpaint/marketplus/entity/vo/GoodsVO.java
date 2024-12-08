package top.wpaint.marketplus.entity.vo;

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
public class GoodsVO implements Serializable {
    private BigInteger id;
    private String name;
    private String description;
    private String image;
    private BigInteger price;
    private BigInteger total;
    private Long count;
}
