package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.AuthConst;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.constant.RoleConst;
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

    public UserAuthServiceImpl(UserMapper userMapper, UserAuthMapper userAuthMapper,
            UserAuthServiceSupport userAuthServiceSupport, VerCodeUtil verCodeUtil) {
        this.userMapper = userMapper;
        this.userAuthMapper = userAuthMapper;
        this.userAuthServiceSupport = userAuthServiceSupport;
        this.verCodeUtil = verCodeUtil;
    }

    @Override
    public LoginVO doLogin(LoginDTO body) throws AppException {
        log.debug("登陆接口 - Service -- {}", body);

        // 账号检测
        // 目前实现了 email，
        // TODO 第三方登陆实现

        LoginVO vo = null;

        if (!body.getType().equals(AuthConst.AUTH_EMAIL)) {
            log.error("其他形式登陆功能未实现");
            throw new AppException(Status.ERROR);
        }

        // 此时 accessKey 为 email
        User user = QueryChain.of(userMapper).select(UserTableDef.USER.DEFAULT_COLUMNS).from(UserTableDef.USER)
                .where(UserTableDef.USER.EMAIL.eq(body.getAccessKey())).one();

        if (null == user) {
            throw new AppException(Status.USER_NOT_FOUND);
        }

        if (user.getIsEnable().equals(LogicConst.DISABLE)) {
            throw new AppException(Status.USER_NOT_ENABLE);
        }

        // 查询用户登陆
        UserAuth userAuth = QueryChain.of(userAuthMapper).select(UserAuthTableDef.USER_AUTH.DEFAULT_COLUMNS)
                .from(UserAuthTableDef.USER_AUTH).where(UserAuthTableDef.USER_AUTH.USER_ID.eq(user.getUserId())
                        .and(UserAuthTableDef.USER_AUTH.AUTH_TYPE.eq(body.getType())))
                .one();

        if (null == userAuth) {
            throw new AppException(Status.USER_AUTH_TYPE_NOT_SUPPORT);
        }

        // email login
        vo = userAuthServiceSupport.emailLogin(body, userAuth);

        return vo;
    }

    @Override
    public VerifyCodeVO doGetVerifyCode(VerifyCodeDTO verifyCode) throws AppException {
        log.debug("获得验证码 - Service");

        // 发送验证码
        Integer verCode = VerCodeUtil.genVerifyCode();

        log.info("verCode = {}", verCode);

        // test
        // Boolean isSendOk = true;
        Boolean isSendOk = verCodeUtil.sendSimpleMessage(verifyCode.getEmail(), verCode.toString());

        if (isSendOk) {
            // VerifyCodeHolder.add(verifyCode.getEmail(), verCode);
            // 将邮箱设置为 sessionId
            SaSessionCustomUtil.getSessionById("verCode-" + verifyCode.getEmail()).set(verifyCode.getEmail(), verCode);
            return new VerifyCodeVO(Status.SEND_MAIL_OK.getMessage());
        }

        throw new AppException(Status.ERROR);
    }

    @Override
    public String doRegister(RegisterDTO body) throws AppException {
        // 检查用户是否存在
        User user = QueryChain.of(userMapper).select(UserTableDef.USER.DEFAULT_COLUMNS).from(UserTableDef.USER)
                .where(UserTableDef.USER.EMAIL.eq(body.getEmail())).one();

        if (null != user) {
            log.warn("用户已经存在 - {}", body.getEmail());
            throw new AppException(Status.USER_EXISTS);
        }

        // 开始注册流程

        SaSession sessionById = SaSessionCustomUtil.getSessionById("verCode-" + body.getEmail());

        Integer verCode = sessionById.getInt(body.getEmail());

        if (verCode.equals(0)) {
            throw new AppException(Status.MAIL_NOT_SEND);
        }

        // 验证码匹配
        // TODO 优化这个流程
        if (!verCode.equals(body.getVerCode())) {
            throw new AppException(Status.VERIFY_CODE_NOT_EQ);
        }

        user = User.builder().userId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
                .email(body.getEmail()).authType(AuthConst.AUTH_EMAIL).roleName(RoleConst.R_USER).build();

        UserAuth userAuth = UserAuth.builder().userId(user.getUserId()).accessKey(body.getEmail())
                .secretKey(body.getPassword()).authName(AuthConst.A_EMAIL_NAME).authType(AuthConst.AUTH_EMAIL)
                .description("邮箱登陆").build();

        userMapper.insert(user);
        userAuthMapper.insert(userAuth);

        // 需要优化这一步
        user.setIsEnable(LogicConst.ENABLE);
        userMapper.update(user);
        userAuth.setIsEnable(LogicConst.ENABLE);
        userAuthMapper.update(userAuth);

        // 重置邮箱验证码
        SaSessionCustomUtil.deleteSessionById("verCode-" + body.getEmail());

        return Status.REGISTER_OK.getMessage();
    }
}
