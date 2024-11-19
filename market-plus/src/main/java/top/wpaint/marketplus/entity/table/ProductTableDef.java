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
public class ProductTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final ProductTableDef PRODUCT = new ProductTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 商品名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 商品价格
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    /**
     * 商品图片
     */
    public final QueryColumn PICTURE = new QueryColumn(this, "picture");

    /**
     * 商品所属商户ID
     */
    public final QueryColumn STORE_ID = new QueryColumn(this, "store_id");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 商品数量
     */
    public final QueryColumn QUANTITY = new QueryColumn(this, "quantity");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 商品 ID
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

    /**
     * 商品描述
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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PRODUCT_ID, STORE_ID, NAME, DESCRIPTION, PICTURE, PRICE, QUANTITY, GMT_CREATED, GMT_MODIFIED, IS_DELETED, IS_ENABLE};

    public ProductTableDef() {
        super("", "wb_product");
    }

    private ProductTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ProductTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ProductTableDef("", "wb_product", alias));
    }

}
