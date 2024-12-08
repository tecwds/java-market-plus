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
public class GoodsTagTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final GoodsTagTableDef GOODS_TAG = new GoodsTagTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn TAG_ID = new QueryColumn(this, "tag_id");

    
    public final QueryColumn GOODS_ID = new QueryColumn(this, "goods_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GOODS_ID, TAG_ID, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public GoodsTagTableDef() {
        super("", "wb_goods_tag");
    }

    private GoodsTagTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GoodsTagTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GoodsTagTableDef("", "wb_goods_tag", alias));
    }

}
