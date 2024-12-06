package top.wpaint.marketplus.common.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum RoleConst {
    USER("R_USER", "普通用户"),
    SELLER("R_SELLER", "商家"),
    GUEST("R_GUEST", "访客");

    private String roleName;
    private String description;

    RoleConst(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public static List<RoleConst> getRoleList() {
        return Arrays.stream(RoleConst.values()).toList();
    }

}