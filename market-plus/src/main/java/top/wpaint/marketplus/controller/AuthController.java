package top.wpaint.marketplus.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.ResponseStatus;
import top.wpaint.marketplus.entity.ResponseEntity;
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

    @GetMapping("verifycode")
    public ResponseEntity<VerifyCodeVO> getVerifyCode(@RequestParam(name = "email") VerifyCodeDTO verifyCode) throws AppException {
        log.debug("执行获得验证码");
        VerifyCodeVO res = userAuthService.doGetVerifyCode(verifyCode);
        return ResponseEntity.success(res);
    }

    @PostMapping("register")
    public ResponseEntity<String> authRegister(@RequestBody RegisterDTO body) throws AppException {
        if (!body.getPassword().equals(body.getRePassword())) {
            return ResponseEntity.error(
                    ResponseStatus.TWICE_PASSWD_NOT_EQ.getCode(),
                    ResponseStatus.TWICE_PASSWD_NOT_EQ.getMessage());
        }
        return ResponseEntity.success(userAuthService.doRegister(body));
    }

    @PostMapping("login")
    public ResponseEntity<LoginVO> authLogin(@RequestBody LoginDTO body) throws AppException {
//        log.debug("执行登陆接口：{}", body);
        // TODO 已登陆用户应直接放行
        if (StpUtil.isLogin()) {
            log.info("已经登录过了...");
            return ResponseEntity.success(new LoginVO(StpUtil.getTokenValue()));
        }

        LoginVO res = userAuthService.doLogin(body);
        return ResponseEntity.success(res);
    }

    @GetMapping("logout")
    public ResponseEntity<String> authLogout() {
        if (!StpUtil.isLogin()) {
            log.warn("这老弟就没登陆...");
            return ResponseEntity.error(
                    ResponseStatus.USER_NOT_LOGIN.getCode(),
                    ResponseStatus.USER_NOT_LOGIN.getMessage());
        }

        StpUtil.logout();
        return ResponseEntity.success(ResponseStatus.SUCCESS.getMessage());
    }
}