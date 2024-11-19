package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

public class StoreDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 店铺所有者ID
     */
    private BigInteger userId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺详细信息
     */
    private String description;
}
