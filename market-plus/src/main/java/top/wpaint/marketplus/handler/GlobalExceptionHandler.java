package top.wpaint.marketplus.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.wpaint.marketplus.entity.ResponseEntity;

@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理
     */
//    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
//        log.error(e.getMessage());
        return ResponseEntity.error();
    }
}