package top.wpaint.marketplus.entity.dto;

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
public class GoodsDTO implements Serializable {
    private BigInteger id;
    private String name;
    private String description;

    /**
     * 分
     */
    private BigInteger price;
    private String image;
}
