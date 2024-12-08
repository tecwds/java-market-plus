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
public class InventoryTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final InventoryTableDef INVENTORY = new InventoryTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 库存数量
     */
    public final QueryColumn COUNT = new QueryColumn(this, "count");

    /**
     * 价格（冗余，加快访问）
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    /**
     * 关联商品 ID
     */
    public final QueryColumn GOODS_ID = new QueryColumn(this, "goods_id");

    /**
     * 关联商店ID
     */
    public final QueryColumn STORE_ID = new QueryColumn(this, "store_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, STORE_ID, GOODS_ID, PRICE, COUNT, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public InventoryTableDef() {
        super("", "wb_inventory");
    }

    private InventoryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public InventoryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new InventoryTableDef("", "wb_inventory", alias));
    }

}
