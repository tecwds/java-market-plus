package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-12-05
 */
public class ProductCategoryTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final ProductCategoryTableDef PRODUCT_CATEGORY = new ProductCategoryTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 关联商品 ID
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 关联分类ID
     */
    public final QueryColumn CATEGORY_ID = new QueryColumn(this, "category_id");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PRODUCT_ID, CATEGORY_ID, GMT_CREATED, GMT_MODIFIED, IS_DELETED, IS_ENABLE};

    public ProductCategoryTableDef() {
        super("", "wb_product_category");
    }

    private ProductCategoryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ProductCategoryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ProductCategoryTableDef("", "wb_product_category", alias));
    }

}
