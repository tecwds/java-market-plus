package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.common.ResponseStatus;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.table.UserTableDef;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.mapper.UserMapper;
import top.wpaint.marketplus.service.UserService;
import org.springframework.stereotype.Service;
import top.wpaint.marketplus.service.support.UserServiceSupport;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    private final UserServiceSupport userServiceSupport;

    public UserServiceImpl(UserMapper userMapper,
                           UserServiceSupport userServiceSupport) {
        this.userMapper = userMapper;
        this.userServiceSupport = userServiceSupport;
    }

    @Override
    public UserInfoVO doGetUserInfo(String userId) throws AppException {
        // 进入到这个方法，说明已经登录过了

        User user = userServiceSupport.getUserById(userId);

        UserInfoVO vo = new UserInfoVO();
        BeanUtil.copyProperties(user, vo);
        return vo;
    }

    @Override
    public UserInfoVO doUpdateUserInfo(String userId, UserInfoDTO body) throws AppException {
        User user = userServiceSupport.getUserById(userId);

        UserInfoVO vo = new UserInfoVO();

        BeanUtil.copyProperties(body, user);
        BeanUtil.copyProperties(user, vo);

        return vo;
    }
}
