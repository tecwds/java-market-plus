package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenStoreDTO implements Serializable {
    /**
     * 店名
     */
    private String name;

    /**
     * 店描述
     */
    private String description;
}
