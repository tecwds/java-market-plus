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
public class UserAddressTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final UserAddressTableDef USER_ADDRESS = new UserAddressTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 市
     */
    public final QueryColumn CITY = new QueryColumn(this, "city");

    /**
     * 姓名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 详细地址
     */
    public final QueryColumn DETAIL = new QueryColumn(this, "detail");

    /**
     * 手机号码
     */
    public final QueryColumn MOBILE = new QueryColumn(this, "mobile");

    /**
     * 关联用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 区
     */
    public final QueryColumn COUNTRY = new QueryColumn(this, "country");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 省
     */
    public final QueryColumn PROVINCE = new QueryColumn(this, "province");

    /**
     * 地址 ID
     */
    public final QueryColumn ADDRESS_ID = new QueryColumn(this, "address_id");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ADDRESS_ID, USER_ID, NAME, MOBILE, PROVINCE, CITY, COUNTRY, DETAIL, GMT_CREATED, GMT_MODIFIED, IS_ENABLE};

    public UserAddressTableDef() {
        super("", "wb_user_address");
    }

    private UserAddressTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UserAddressTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UserAddressTableDef("", "wb_user_address", alias));
    }

}
