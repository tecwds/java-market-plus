package top.wpaint.marketplus.common.constant;

import lombok.Getter;

@Getter
public enum Gender {
    UNKNOWN(1, "未知"),
    MALE(2, "男"),
    FEMALE(3, "女"),
    WILMA(4, "沃尔玛购物袋"),
    ;

    private final Integer code;
    private final String name;

    Gender(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}