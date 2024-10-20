package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-10-21
 */
public class UserAuthTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final UserAuthTableDef USER_AUTH = new UserAuthTableDef();

    /**
     * 表 ID，用于快速索引
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 关联用户 ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 认证名称
     */
    public final QueryColumn AUTH_NAME = new QueryColumn(this, "auth_name");

    /**
     * 认证类型
     */
    public final QueryColumn AUTH_TYPE = new QueryColumn(this, "auth_type");

    /**
     * 是否启用（激活）
     */
    public final QueryColumn IS_ENABLE = new QueryColumn(this, "is_enable");

    /**
     * 登录时的用户名、邮箱或者第三方Token
     */
    public final QueryColumn ACCESS_KEY = new QueryColumn(this, "access_key");

    /**
     * 是否删除（逻辑删除）
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 密码，如果存在
     */
    public final QueryColumn SECRET_KEY = new QueryColumn(this, "secret_key");

    /**
     * 创建时间
     */
    public final QueryColumn GMT_CREATED = new QueryColumn(this, "gmt_created");

    /**
     * 认证描述
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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, AUTH_NAME, AUTH_TYPE, DESCRIPTION, ACCESS_KEY, SECRET_KEY, GMT_CREATED, GMT_MODIFIED, IS_ENABLE};

    public UserAuthTableDef() {
        super("", "wb_user_auth");
    }

    private UserAuthTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UserAuthTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UserAuthTableDef("", "wb_user_auth", alias));
    }

}
