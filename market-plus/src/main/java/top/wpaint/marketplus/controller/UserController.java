package top.wpaint.marketplus.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.dto.UserPasswdDTO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.service.UserService;

@Slf4j
@SaCheckLogin
//@RestController
//@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查 - 用户信息
     */
    @GetMapping("me")
    public Result<UserInfoVO> getUserInfo() throws AppException {
        log.debug("用户 ID 测试 - {}", StpUtil.getLoginIdAsString());
        return Result.success(userService.doGetUserInfo(StpUtil.getLoginIdAsString()));
    }

    /**
     * 改 - 用户信息
     */
    @PostMapping("info")
    public Result<UserInfoVO> updateUserInfo(@RequestBody UserInfoDTO body) throws AppException {
        log.debug("用户信息测试 - {}", StpUtil.getLoginIdAsString());
        return Result.success(userService.doUpdateUserInfo(StpUtil.getLoginIdAsString(), body));
    }

    /**
     * 改 - 用户密码
     * @param body 用户密码信息
     * @return 成功
     * @throws AppException 通用异常
     */
    @PostMapping("passwd")
    public Result<String> updateUserPasswd(@RequestBody UserPasswdDTO body) throws AppException {
        log.debug("用户密码测试 - {}", StpUtil.getLoginIdAsString());

        if (!body.getNewPasswd().equals(body.getConfirmPasswd())) {
            return Result.error(
                    Status.TWICE_PASSWD_NOT_EQ.getCode(),
                    Status.TWICE_PASSWD_NOT_EQ.getMessage());
        }

        return Result.success(userService.doUpdateUserPasswd(StpUtil.getLoginIdAsString(), body));
    }


}