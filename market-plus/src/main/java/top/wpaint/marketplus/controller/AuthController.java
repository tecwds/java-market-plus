package top.wpaint.marketplus.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.AppException;
import top.wpaint.marketplus.common.ResponseStatus;
import top.wpaint.marketplus.entity.ResponseEntity;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.VerifyCodeDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.VerifyCodeVO;
import top.wpaint.marketplus.service.AuthService;
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
        LoginVO res = userAuthService.doLogin(body);
        return ResponseEntity.success(res);
    }
}