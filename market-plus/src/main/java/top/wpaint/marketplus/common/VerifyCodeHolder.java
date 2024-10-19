package top.wpaint.marketplus.common;

public class VerifyCodeHolder {
    private final static ThreadLocal<Integer> verifycode = new ThreadLocal<>();

    public static void add(Integer code) {
        verifycode.set(code);
    }

    public static Integer getCode() {
        return verifycode.get();
    }

    public static void remove() {
        verifycode.remove();
    }

    public static Boolean verify(Integer code) {
        return verifycode.get().equals(code);
    }
}