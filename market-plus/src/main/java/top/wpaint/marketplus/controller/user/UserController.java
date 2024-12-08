package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.RoleConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.OpenStoreDTO;
import top.wpaint.marketplus.entity.dto.ResetPasswdDTO;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

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

    @PutMapping("openStore")
    public Result<String> openStore(@RequestBody OpenStoreDTO store) throws AppException {
        log.info("开新店了 -- {}，信息：{}", StpUtil.getLoginIdAsString(), store);

        // 更新个人信息（变成店长啦）
        User u = userServiceSupport.getUserFromStorage();
        u.setRoleName(RoleConst.SELLER.getRoleName());
        userService.updateById(u);

        return Result.success(storeService.doOpenStore(u.getId(), store));
    }
}
