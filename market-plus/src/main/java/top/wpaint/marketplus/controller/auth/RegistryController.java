package top.wpaint.marketplus.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.dto.RegisterDTO;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class RegistryController extends BaseController {

    /**
     * 获得邮箱验证码
     * @param email 邮箱
     * @return 空
     * @throws AppException 自定义应用异常
     */
    @GetMapping("verifyCode")
    public Result<String> getVerifyCode(String email) throws AppException {
        log.info("获得邮箱验证码 -- {}", email);
        userService.doGetVerifyCode(email);
        return Result.success();
    }

    @PostMapping("register")
    public Result<String> register(@RequestBody RegisterDTO register) throws AppException {
        log.info("用户注册 -- {}", register);

        // 两次密码不一样则注册直接失败
        if (!register.getPassword().equals(register.getRePassword())) {
            return Result.error(Status.TWICE_PASSWD_NOT_EQ);
        }

        userService.doRegister(register);
        return Result.success();
    }

}
