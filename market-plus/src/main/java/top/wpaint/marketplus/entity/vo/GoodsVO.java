package top.wpaint.marketplus.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger id;
    private String name;
    private String description;
    private String image;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger price;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger total;

    private Long count;
}
