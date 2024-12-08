package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-12-08
 */
public class OrderTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final OrderTableDef ORDER = new OrderTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 数量
     */
    public final QueryColumn COUNT = new QueryColumn(this, "count");

    /**
     * 单价
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    /**
     * 总价
     */
    public final QueryColumn TOTAL = new QueryColumn(this, "total");

    /**
     * 关联用户 ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 关联商品ID
     */
    public final QueryColumn GOODS_ID = new QueryColumn(this, "goods_id");

    /**
     * 订单 ID
     */
    public final QueryColumn ORDER_ID = new QueryColumn(this, "order_id");

    /**
     * 逻辑删除
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 是否启用
     */
    public final QueryColumn IS_ENABLED = new QueryColumn(this, "is_enabled");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

    /**
     * 更新日期
     */
    public final QueryColumn GMT_MODIFIED = new QueryColumn(this, "gmt_modified");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ORDER_ID, USER_ID, GOODS_ID, PRICE, TOTAL, COUNT, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public OrderTableDef() {
        super("", "wb_order");
    }

    private OrderTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public OrderTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new OrderTableDef("", "wb_order", alias));
    }

}
