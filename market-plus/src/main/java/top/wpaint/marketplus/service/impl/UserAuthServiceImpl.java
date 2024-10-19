package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.constant.AuthConst;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.table.UserAuthTableDef;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.mapper.UserAuthMapper;
import top.wpaint.marketplus.service.UserAuthService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-10-19
 */
@Slf4j
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    private final UserAuthMapper userAuthMapper;

    public UserAuthServiceImpl(UserAuthMapper userAuthMapper) {
        this.userAuthMapper = userAuthMapper;
    }

    @Override
    public LoginVO doLogin(LoginDTO body) {
        log.debug("登陆接口 - Service -- {}", body);

        LoginVO vo = null;

        List<UserAuth> userAuth = QueryChain.of(userAuthMapper)
                .select(UserAuthTableDef.USER_AUTH.ALL_COLUMNS)
                .from(UserAuthTableDef.USER_AUTH)
                .where(UserAuthTableDef.USER_AUTH.USER_NAME
                        .eq(body.getAccessKey())
                        .or(UserAuthTableDef.USER_AUTH.ACCESS_KEY.eq(body.getAccessKey())))
                .list();

        List<UserAuth> all = QueryChain.of(userAuthMapper)
            .select(UserAuthTableDef.USER_AUTH.ALL_COLUMNS)
            .from(UserAuthTableDef.USER_AUTH)
            .list();

        // 检查认证类型
        if (!Objects.equals(body.getType(), AuthConst.AUTH_EMAIL)) {
            return null;
        }

        UserAuth emailAuth = null;

        for (UserAuth ua : userAuth) {
            if (ua.getAuthType().equals(body.getType())) {
                emailAuth = ua;
                break;
            }
        }

        if (null == emailAuth) {
            return null;
        }

        if (emailAuth.getAccessKey().equals(body.getAccessKey()) &&
                emailAuth.getSecretKey().equals(body.getSecretKey())
        ) {
            StpUtil.login(emailAuth.getUserId().toString());
            vo = LoginVO.builder().token(StpUtil.getTokenValue()).build();
        }

        return vo;
    }
}
