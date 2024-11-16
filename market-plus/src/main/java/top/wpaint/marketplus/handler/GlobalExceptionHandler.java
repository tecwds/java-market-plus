package top.wpaint.marketplus.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Result;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 应用异常处理
     */
    @ResponseBody
    @ExceptionHandler(AppException.class)
    public Result<String> appExceptionHandler(AppException e) {
        log.error(e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
}