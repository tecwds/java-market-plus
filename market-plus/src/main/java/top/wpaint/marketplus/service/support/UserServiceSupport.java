package top.wpaint.marketplus.service.support;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.UserInfoStorage;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.mapper.UserMapper;

@Slf4j
@Component
public class UserServiceSupport {

    @Resource
    private UserMapper userMapper;

    public User getUserFromStorage() throws AppException {
        User u = userMapper.selectOneById(UserInfoStorage.getUserId());

        if (null == u) {
            // 这里可能没有登陆，直接返回未登录
            throw new AppException(Status.USER_NOT_LOGIN);
        }

        return u;
    }
}