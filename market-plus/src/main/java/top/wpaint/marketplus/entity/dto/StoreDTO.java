package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 登陆检测，不需要额外传 userId
    // private BigInteger userId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺详细信息
     */
    private String description;
}
