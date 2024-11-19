package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-11-19
 */
public class OrderTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final OrderTableDef ORDER = new OrderTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 成交单价
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 订单 ID
     */
    public final QueryColumn ORDER_ID = new QueryColumn(this, "order_id");

    /**
     * 购买来源店铺ID
     */
    public final QueryColumn STORE_ID = new QueryColumn(this, "store_id");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 成交数量
     */
    public final QueryColumn QUANTITY = new QueryColumn(this, "quantity");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 购买的商品ID
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 店铺名称
     */
    public final QueryColumn STORE_NAME = new QueryColumn(this, "store_name");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

    /**
     * 总成交价格
     */
    public final QueryColumn TOTAL_PRICE = new QueryColumn(this, "total_price");

    /**
     * 修改时间
     */
    public final QueryColumn GMT_MODIFIED = new QueryColumn(this, "gmt_modified");

    /**
     * 商品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ORDER_ID, USER_ID, STORE_ID, PRODUCT_ID, STORE_NAME, PRODUCT_NAME, PRICE, QUANTITY, TOTAL_PRICE, GMT_CREATED, GMT_MODIFIED, IS_DELETED, IS_ENABLE};

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
