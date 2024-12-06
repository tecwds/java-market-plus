package top.wpaint.marketplus.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author tecwds
 * @since 2024-12-06
 */
public class UserTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final UserTableDef USER = new UserTableDef();

    /**
     * 表的 ID 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 邮箱
     */
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    /**
     * 头像地址
     */
    public final QueryColumn AVATAR = new QueryColumn(this, "avatar");

    /**
     * 性别
     */
    public final QueryColumn GENDER = new QueryColumn(this, "gender");

    /**
     * 昵称
     */
    public final QueryColumn NICKNAME = new QueryColumn(this, "nickname");

    /**
     * 密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 角色名称
     */
    public final QueryColumn ROLE_NAME = new QueryColumn(this, "role_name");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 逻辑删除
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * 是否启用
     */
    public final QueryColumn IS_ENABLED = new QueryColumn(this, "is_enabled");

    /**
     * 签名
     */
    public final QueryColumn SIGNATURE = new QueryColumn(this, "signature");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, EMAIL, PASSWORD, AVATAR, USERNAME, NICKNAME, SIGNATURE, GENDER, ROLE_NAME, GMT_CREATED, GMT_MODIFIED, IS_ENABLED};

    public UserTableDef() {
        super("", "wb_user");
    }

    private UserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UserTableDef("", "wb_user", alias));
    }

}
