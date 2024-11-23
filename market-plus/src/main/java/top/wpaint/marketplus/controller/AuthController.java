package top.wpaint.marketplus.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.VerifyCodeDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.VerifyCodeVO;
import top.wpaint.marketplus.service.UserAuthService;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    /**
     * 获得验证码
     * @param verifyCode
     * @return
     * @throws AppException
     */
    @GetMapping("verifycode")
    public Result<VerifyCodeVO> getVerifyCode(@RequestParam(name = "email") VerifyCodeDTO verifyCode) throws AppException {
        log.debug("执行获得验证码");
        VerifyCodeVO res = userAuthService.doGetVerifyCode(verifyCode);
        return Result.success(res);
    }

    /**
     * 注册
     * @param body
     * @return
     * @throws AppException
     */
    @PostMapping("register")
    public Result<String> authRegister(@RequestBody RegisterDTO body) throws AppException {
        if (!body.getPassword().equals(body.getRePassword())) {
            return Result.error(
                    Status.TWICE_PASSWD_NOT_EQ.getCode(),
                    Status.TWICE_PASSWD_NOT_EQ.getMessage());
        }
        return Result.success(userAuthService.doRegister(body));
    }

    /**
     * 登陆
     * @param body
     * @return
     * @throws AppException
     */
    @PostMapping("login")
    public Result<LoginVO> authLogin(@RequestBody LoginDTO body) throws AppException {
//        log.debug("执行登陆接口：{}", body);
        if (StpUtil.isLogin()) {
            log.info("已经登录过了...");
            return Result.success(new LoginVO(StpUtil.getTokenValue()));
        }

        LoginVO res = userAuthService.doLogin(body);
        return Result.success(res);
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("logout")
    public Result<String> authLogout() {
        if (!StpUtil.isLogin()) {
            log.warn("这老弟就没登陆...");
            return Result.error(
                    Status.USER_NOT_LOGIN.getCode(),
                    Status.USER_NOT_LOGIN.getMessage());
        }

        StpUtil.logout();
        return Result.success(Status.SUCCESS.getMessage());
    }
}