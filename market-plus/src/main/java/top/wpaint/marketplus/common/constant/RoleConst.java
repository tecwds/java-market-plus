package top.wpaint.marketplus.common.constant;

import java.util.ArrayList;
import java.util.List;

public final class RoleConst {
    /**
     * 普通用户
     */
    public static final String R_USER = "R_USER";
    public static final String R_USER_DES = "普通用户";

    /**
     * 系统管理员
     */
    public static final String R_ADMIN = "R_ADMIN";
    public static final String R_ADMIN_DES = "系统管理员";

    /**
     * 商家
     */
    public static final String R_SELLER = "R_SELLER";
    public static final String R_SELLER_DES = "商家";

    /**
     * 访客
     */
    public static final String R_GUEST = "R_GUEST";
    public static final String R_GUEST_DES = "访客";

    /**
     * 客服
     */
    public static final String R_SERVICE = "R_SERVICE";
    public static final String R_SERVICE_DES = "客服";

    public static final List<String> ROLE_LIST;

    static {
        ROLE_LIST = new ArrayList<>(5);
        ROLE_LIST.add("R_USER");
        ROLE_LIST.add("R_ADMIN");
        ROLE_LIST.add("R_SELLER");
        ROLE_LIST.add("R_GUEST");
        ROLE_LIST.add("R_SERVICE");
    }
}