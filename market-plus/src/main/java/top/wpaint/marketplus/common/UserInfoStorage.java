package top.wpaint.marketplus.common;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserInfoStorage {
    private static final ThreadLocal<Map<String, String>> userInfo;

    static {
        userInfo = new ThreadLocal<>();
        userInfo.set(new HashMap<>());
    }

    public static void add(String key, String value) {
        log.info("添加 ThreadLocal 信息 -- key： {}, value： {}", key, value);
        userInfo.get().put(key, value);
    }

    public static void addUserId(String value) {
        log.info("保存用户 ID 信息 -- value： {}", value);
        add("userId", value);
    }

    public static void remove(String key) {
        log.info("删除 ThreadLocal 信息 -- key： {}", key);
        userInfo.get().remove(key);
    }

}
