package top.wpaint.marketplus.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.entity.ResponseEntity;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
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

    @PostMapping("login")
    public ResponseEntity<LoginVO> authLogin(@RequestBody LoginDTO body) {
        log.debug("执行登陆接口：{}", body);
        LoginVO res = userAuthService.doLogin(body);
        return ResponseEntity.success(res);
    }
}