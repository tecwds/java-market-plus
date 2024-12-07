package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;

/**
 *  实体类。
 *
 * @author tecwds
 * @since 2024-12-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "template")
public class Template implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreated;

    /**
     * 表的 ID 主键
     */
    private BigInteger id;

    /**
     * 更新日期
     */
    private LocalDateTime gmtModified;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

    /**
     * 是否启用
     */
    private Integer isEnabled;

}
