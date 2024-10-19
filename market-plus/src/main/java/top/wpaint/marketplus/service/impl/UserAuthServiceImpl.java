package top.wpaint.marketplus.service.impl;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.AppException;
import top.wpaint.marketplus.common.ResponseStatus;
import top.wpaint.marketplus.common.VerifyCodeHolder;
import top.wpaint.marketplus.common.constant.AuthConst;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.VerifyCodeDTO;
import top.wpaint.marketplus.entity.table.UserAuthTableDef;
import top.wpaint.marketplus.entity.table.UserTableDef;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.VerifyCodeVO;
import top.wpaint.marketplus.mapper.UserAuthMapper;
import top.wpaint.marketplus.mapper.UserMapper;
import top.wpaint.marketplus.service.UserAuthService;
import org.springframework.stereotype.Service;
import top.wpaint.marketplus.service.support.UserAuthServiceSupport;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;
import top.wpaint.marketplus.util.VerCodeUtil;

import java.math.BigInteger;
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
    private final UserMapper userMapper;
    private final UserAuthServiceSupport userAuthServiceSupport;

    private final VerCodeUtil verCodeUtil;

    public UserAuthServiceImpl(
        UserMapper userMapper,
        UserAuthMapper userAuthMapper,
        UserAuthServiceSupport userAuthServiceSupport, VerCodeUtil verCodeUtil) {
        this.userMapper = userMapper;
        this.userAuthMapper = userAuthMapper;
        this.userAuthServiceSupport = userAuthServiceSupport;
        this.verCodeUtil = verCodeUtil;
    }

    @Override
    public LoginVO doLogin(LoginDTO body) throws AppException {
        log.debug("登陆接口 - Service -- {}", body);

        LoginVO vo = null;

        User user = QueryChain.of(userMapper)
            .select(UserTableDef.USER.DEFAULT_COLUMNS)
            .from(UserTableDef.USER)
            .where(UserTableDef.USER.USERNAME.eq(body.getUsername()))
            .one();

        if (null == user) {
            log.warn(ResponseStatus.USER_NOT_FOUND.getMessage());
            throw new AppException(ResponseStatus.USER_NOT_FOUND);
        }

        // 根据 username 和 (auth_)type 获得 user_auth
        // 理想结果为之查询出一条数据
        UserAuth userAuth = QueryChain.of(userAuthMapper)
                .select(UserAuthTableDef.USER_AUTH.DEFAULT_COLUMNS)
                .from(UserAuthTableDef.USER_AUTH)
                .where(UserAuthTableDef.USER_AUTH.USERNAME.eq(body.getUsername())
                        .and(UserAuthTableDef.USER_AUTH.AUTH_TYPE.eq(body.getType())))
                .one();

        if (null == userAuth) {
            log.warn(ResponseStatus.USER_NOT_ENABLE.getMessage());
            throw new AppException(ResponseStatus.USER_NOT_ENABLE);
        }

        // 账号检测
        // 目前实现了 email，
        // TODO 第三方登陆实现

        if (body.getType().equals(AuthConst.AUTH_EMAIL)) {
            vo = userAuthServiceSupport.emailLogin(body, userAuth);
        } else {
            throw new AppException(ResponseStatus.ERROR);
        }

        return vo;
    }

    @Override
    public VerifyCodeVO doGetVerifyCode(VerifyCodeDTO verifyCode) throws AppException {
        log.debug("获得验证码 - Service");

        // 发送验证码
        Integer verCode = VerCodeUtil.genVerifyCode();

        Boolean isSendOk = verCodeUtil.sendSimpleMessage(verifyCode.getEmail(),verCode.toString());

        if (isSendOk) {
            VerifyCodeHolder.add(verCode);
            return new VerifyCodeVO(ResponseStatus.SEND_MAIL_OK.getMessage());
        }

        throw new AppException(ResponseStatus.ERROR);
    }

    @Override
    public String doRegister(RegisterDTO body) throws AppException {
        // 检查用户是否存在
        User user = QueryChain.of(userMapper)
            .select(UserTableDef.USER.DEFAULT_COLUMNS)
            .from(UserTableDef.USER)
            .where(UserTableDef.USER.USERNAME.eq(body.getEmail()))
            .one();

        if (null != user) {
            log.warn("用户已经存在 - {}", body.getEmail());
            throw new AppException(ResponseStatus.USER_EXISTS);
        }

        // 开始注册流程

        Integer verCode = VerifyCodeHolder.getCode();

        if (verCode.equals(127)) {
            throw new AppException(ResponseStatus.MAIL_NOT_SEND);
        }

        // 验证码匹配
        if (!verCode.equals(body.getVerCode())) {
            throw new AppException(ResponseStatus.VERIFY_CODE_NOT_EQ);
        }

        UserAuth userAuth = new UserAuth();
        
        user = User.builder()
                .userId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
        .authType(AuthConst.AUTH_EMAIL)
        .rule()
                .build();





        return null;
    }
}
