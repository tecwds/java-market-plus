package top.wpaint.marketplus.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(Status.SUCCESS.getCode())
                .message(Status.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static Result<String> success() {
        return success(null);
    }

    public static <T> Result<T> error(int code, String message) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static Result<String> error(Status status) {
        return error(status.getCode(), status.getMessage());
    }

    public static <T> Result<T> error(int code) {
        return error(code, Status.ERROR.getMessage());
    }

    public static <T> Result<T> error(String message) {
        return error(Status.ERROR.getCode(), message);
    }
}
