package top.wpaint.marketplus.controller.auth;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;

@Slf4j
@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api/auth")
public class LoginController extends BaseController {

    @PostMapping("login")
    public Result<LoginVO> login(@RequestBody LoginDTO login) throws AppException {
        log.info("用户登陆 -- {}", login.getEmail());

        // 已经登陆快速返回 Token
        if (StpUtil.isLogin(login.getEmail())) {
            log.info("这个用户已经登陆了 -- {}", login.getEmail());
            StpUtil.logout(login.getEmail());
        }

        return Result.success(userService.doLogin(login));
    }

    @GetMapping("logout")
    public Result<String> logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
        }
        return Result.success();
    }

}
