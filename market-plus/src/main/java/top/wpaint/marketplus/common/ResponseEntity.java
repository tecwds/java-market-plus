package top.wpaint.marketplus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.ResponseStatus;

import java.io.Serializable;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResponseEntity<T> success(T data) {
        return ResponseEntity.<T>builder()
                .code(ResponseStatus.SUCCESS.getCode())
                .message(ResponseStatus.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<T> error(int code, String message) {
        return ResponseEntity.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ResponseEntity<T> error(int code) {
        return error(code, ResponseStatus.ERROR.getMessage());
    }

    public static <T> ResponseEntity<T> error(String message) {
        return error(ResponseStatus.ERROR.getCode(), message);
    }

    public static <T> ResponseEntity<T> error() {
        return error(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMessage());
    }
}
