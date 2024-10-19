package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-10-19
 */
public class UserAuthTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final UserAuthTableDef USER_AUTH = new UserAuthTableDef();

    /**
     * 关联认证 ID
     */
    public final QueryColumn AUTH_ID = new QueryColumn(this, "auth_id");

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
     * 认证描述
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{AUTH_ID, USER_ID, AUTH_NAME, AUTH_TYPE, DESCRIPTION};

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
