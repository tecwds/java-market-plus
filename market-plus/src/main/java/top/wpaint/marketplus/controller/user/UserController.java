package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.ResetPasswdDTO;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.table.UserTableDef;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.service.UserService;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("me")
    public Result<UserInfoVO> getInfo() throws AppException {
        log.info("获得个人信息 -- {}", StpUtil.getLoginIdAsString());
        return Result.success(userService.doGetInfo(StpUtil.getLoginIdAsString()));
    }

    @PostMapping("me")
    public Result<UserInfoVO> updateInfo(@RequestBody UserInfoDTO userInfo) throws AppException {
        log.info("更新个人信息 -- {}", StpUtil.getLoginIdAsString());
        return Result.success(userService.doUpdateInfo(userInfo));
    }

    @PostMapping("resetPassword")
    public Result<String> resetPassword(@RequestBody ResetPasswdDTO resetPasswd) throws AppException {
        log.info("重置个人密码 -- 用户：{}", StpUtil.getLoginIdAsString());
        if (!resetPasswd.getNewPassword().equals(resetPasswd.getRePassword())) {
            throw new AppException(Status.TWICE_PASSWD_NOT_EQ);
        }
        return Result.success(userService.doResetPassword(resetPasswd));
    }
}
