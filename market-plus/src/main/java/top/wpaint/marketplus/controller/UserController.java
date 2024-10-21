package top.wpaint.marketplus.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.ResponseEntity;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.service.UserService;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    public ResponseEntity<UserInfoVO> getUserInfo() throws AppException {
        log.debug("用户 ID 测试 - {}", StpUtil.getLoginIdAsString());
        return ResponseEntity.success(userService.doGetUserInfo(StpUtil.getLoginIdAsString()));
    }

    @PostMapping("info")
    public ResponseEntity<UserInfoVO> updateUserInfo(@RequestBody UserInfoDTO body) throws AppException {
        log.debug("用户信息测试 - {}", StpUtil.getLoginIdAsString());
        return ResponseEntity.success(userService.doUpdateUserInfo(StpUtil.getLoginIdAsString(), body));
    }

    
}