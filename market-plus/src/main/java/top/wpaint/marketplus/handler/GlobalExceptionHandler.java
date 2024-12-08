package top.wpaint.marketplus.handler;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import top.wpaint.marketplus.common.Status;
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

    @ResponseBody
    @ExceptionHandler(SaTokenException.class)
    public Result<String> handlerSaTokenException(SaTokenException e) {
        log.error("Sa-token 错误 -- {} -- {}", e.getCode(), e.getMessage());

        // token 无效
        if (e.getCode() == 11012) {
            return Result.error(Status.USER_NOT_LOGIN);
        }

        return Result.error(Status.ERROR.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> ExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return Result.error(Status.ERROR);
    }
}