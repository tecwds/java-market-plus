package top.wpaint.marketplus.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.wpaint.marketplus.common.Status;

import java.io.Serial;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AppException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 异常状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;

    public AppException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppException(Integer code) {
        this.code = code;
    }

    public AppException(Integer code, String message, Throwable cause) {
        super.initCause(cause);
        this.message = message;
        this.code = code;
    }

    public AppException(Integer code, Throwable cause) {
        super.initCause(cause);
        this.code = code;
    }

    public AppException(Status status, Throwable cause) {
        this(status.getCode(), status.getMessage(), cause);
    }

    public AppException(Status status) {
        this(status.getCode(), status.getMessage());
    }


}