package top.wpaint.marketplus.common.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.service.support.UserServiceSupport;

@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    private final UserServiceSupport userServiceSupport;

    public StpInterfaceImpl(UserServiceSupport userServiceSupport) {
        this.userServiceSupport = userServiceSupport;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    /**
     * 返回一个账号的角色标识
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (!(loginId instanceof String)) {
            log.error("getRoleList loginId is not String, will return a empty list -- {}", loginId);
            // 返回空
            return new ArrayList<>();
        }

        User user = null;
        try {
            user = userServiceSupport.getUserById(loginId.toString());    
        }
        catch (AppException e) {
            log.error("no user found -- {}", user);
            return new ArrayList<>();
        }
        
        // 每个用户只有一个角色
        return List.of(user.getRoleName());
    }
}
