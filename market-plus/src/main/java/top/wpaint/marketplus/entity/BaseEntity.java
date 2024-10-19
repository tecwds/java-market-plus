package top.wpaint.marketplus.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import top.wpaint.marketplus.common.constant.LogicConst;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表 ID，用于快速索引
     */
    @Id
    private BigInteger id;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime gmtCreated;

    /**
     * 修改时间
     */
    @Column(onUpdateValue = "now()")
    private LocalDateTime gmtModified;

    /**
     * 是否删除（逻辑删除）
     */
    @Column(isLogicDelete = true, onInsertValue = LogicConst.NOT_DELETED)
    private Integer isDeleted;

    /**
     * 是否启用（激活）
     */
    @Column(onInsertValue = LogicConst.DISABLE)
    private Integer isEnable;
}