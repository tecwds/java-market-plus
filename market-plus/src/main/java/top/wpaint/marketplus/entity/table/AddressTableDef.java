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
public class AddressTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final AddressTableDef ADDRESS = new AddressTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 市
     */
    public final QueryColumn CITY = new QueryColumn(this, "city");

    /**
     * 邮箱
     */
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    /**
     * 电话
     */
    public final QueryColumn PHONE = new QueryColumn(this, "phone");

    /**
     * 详细地址
     */
    public final QueryColumn DETAIL = new QueryColumn(this, "detail");

    /**
     * 关联用户 ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 区
     */
    public final QueryColumn COUNTRY = new QueryColumn(this, "country");

    /**
     * 省
     */
    public final QueryColumn PROVINCE = new QueryColumn(this, "province");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, EMAIL, PHONE, PROVINCE, CITY, COUNTRY, DETAIL, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public AddressTableDef() {
        super("", "wb_address");
    }

    private AddressTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public AddressTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new AddressTableDef("", "wb_address", alias));
    }

}
