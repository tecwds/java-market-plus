package top.wpaint.marketplus.common;

import java.util.HashMap;
import java.util.Optional;

public class VerifyCodeHolder {
    private final static ThreadLocal<HashMap<String, Integer>> verifycode = new ThreadLocal<>();

    static {
        verifycode.set(new HashMap<>());
    }

    public static void add(String email, Integer code) {
        verifycode.get().put(email, code);
    }

    public static Integer getCode(String email) {
        return Optional.ofNullable(verifycode.get().get(email)).orElse(127);
    }

    public static void reset() {
        verifycode.get().clear();
    }

    public static Boolean verify(String email, Integer code) {
        return getCode(email).equals(code);
    }
}