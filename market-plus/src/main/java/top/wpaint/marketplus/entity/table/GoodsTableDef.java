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
public class GoodsTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final GoodsTableDef GOODS = new GoodsTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 商品名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 图片地址
     */
    public final QueryColumn IMAGE = new QueryColumn(this, "image");

    /**
     * 商品价格
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

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
     * 商品描述
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, DESCRIPTION, PRICE, IMAGE, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public GoodsTableDef() {
        super("", "wb_goods");
    }

    private GoodsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GoodsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GoodsTableDef("", "wb_goods", alias));
    }

}
