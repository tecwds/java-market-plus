package top.wpaint.marketplus.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping
    public Result<LoginVO> login(@RequestBody LoginDTO login) throws AppException {
        log.info("用户登陆 -- {}", login.getEmail());

        // 已经登陆快速返回 Token
        if (StpUtil.isLogin(login.getEmail())) {
            log.info("这个用户已经登陆了 -- {}", login.getEmail());
            return Result.success(new LoginVO(StpUtil.getTokenValue()));
        }

        return Result.success(userService.doLogin(login));
    }

}
