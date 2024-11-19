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
public class StoreTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final StoreTableDef STORE = new StoreTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 店铺名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 店铺所有者ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 店铺 ID
     */
    public final QueryColumn STORE_ID = new QueryColumn(this, "store_id");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

    /**
     * 店铺详细信息
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 修改时间
     */
    public final QueryColumn GMT_MODIFIED = new QueryColumn(this, "gmt_modified");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, STORE_ID, USER_ID, NAME, DESCRIPTION, GMT_CREATED, GMT_MODIFIED, IS_DELETED, IS_ENABLE};

    public StoreTableDef() {
        super("", "wb_store");
    }

    private StoreTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public StoreTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new StoreTableDef("", "wb_store", alias));
    }

}
