package top.wpaint.marketplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.AuthConst;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.dto.UserPasswdDTO;
import top.wpaint.marketplus.entity.table.UserAuthTableDef;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.mapper.UserAuthMapper;
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

    private final UserAuthMapper userAuthMapper;

    private final UserServiceSupport userServiceSupport;

    public UserServiceImpl(UserMapper userMapper,
                           UserAuthMapper userAuthMapper,
                           UserServiceSupport userServiceSupport) {
        this.userMapper = userMapper;
        this.userAuthMapper = userAuthMapper;
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

        userMapper.update(user);

        return vo;
    }

    @Override
    public String doUpdateUserPasswd(String userId, UserPasswdDTO body) throws AppException {
        UserAuth userAuth = QueryChain.of(userAuthMapper)
                .select(UserAuthTableDef.USER_AUTH.DEFAULT_COLUMNS)
                .from(UserAuthTableDef.USER_AUTH)
                .where(UserAuthTableDef.USER_AUTH.USER_ID.eq(userId)
                        .and(UserAuthTableDef.USER_AUTH.SECRET_KEY.eq(body.getOldPasswd()))
                        .and(UserAuthTableDef.USER_AUTH.AUTH_TYPE.eq(AuthConst.AUTH_EMAIL))
                        .and(UserAuthTableDef.USER_AUTH.IS_ENABLE.eq(LogicConst.ENABLE))
                )
                .one();

        if (null == userAuth) {
            throw new AppException(Status.OLD_PASSWORD_NOT_EQ);
        }

        userAuth.setSecretKey(body.getNewPasswd());
        userAuthMapper.update(userAuth);

        return Status.SUCCESS.getMessage();
    }
}
