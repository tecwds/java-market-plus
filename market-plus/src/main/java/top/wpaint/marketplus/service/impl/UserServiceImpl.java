package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.UserInfoStorage;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.constant.RoleConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.ResetPasswdDTO;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.table.UserTableDef;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;
import top.wpaint.marketplus.mapper.UserMapper;
import top.wpaint.marketplus.service.UserService;
import org.springframework.stereotype.Service;
import top.wpaint.marketplus.util.VerCodeUtil;

import java.math.BigInteger;
import java.util.Optional;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-10-19
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${market.secret-key}")
    private String secretKey;

    @Resource
    private UserMapper userMapper;

    @Resource
    private VerCodeUtil verCodeUtil;

    @Override
    public void doGetVerifyCode(String email) throws AppException {
        // 发送验证码
        Integer verCode = VerCodeUtil.genVerifyCode();

        log.info("verCode = {}", verCode);

        Boolean isSendOk = verCodeUtil.sendSimpleMessage(email, verCode.toString());

        if (!isSendOk) {
            throw new AppException(Status.SEND_MAIL_ERR);
        }
        SaSessionCustomUtil.getSessionById("verCode-" + email).set(email, verCode);
    }

    @Override
    @Transactional
    public void doRegister(RegisterDTO register) throws AppException {
        User u = userMapper.selectOneByQuery(QueryWrapper.create().where(UserTableDef.USER.EMAIL.eq(register.getEmail())));

        if (null != u) {
            log.warn("用户已经存在 -- {}", register.getEmail());
            throw new AppException(Status.USER_EXISTS);
        }

        // 开始注册
        SaSession session = SaSessionCustomUtil.getSessionById("verCode-" + register.getEmail());

        Integer verCode = session.getInt(register.getEmail());

        if (verCode.equals(0)) {
            throw new AppException(Status.MAIL_NOT_SEND);
        }

        if (!verCode.equals(register.getVerCode())) {
            throw new AppException(Status.VERIFY_CODE_NOT_EQ);
        }

        u = User.builder()
                .email(register.getEmail())
                .password(SaSecureUtil.aesEncrypt(secretKey, register.getPassword()))
                .roleName(RoleConst.USER.getRoleName())
                .isEnabled(LogicConst.ENABLE)
                .build();

        int insert = userMapper.insert(u);

        if (0 == insert) {
            throw new AppException(Status.ERROR);
        }

        SaSessionCustomUtil.deleteSessionById("verCode-" + register.getEmail());
    }

    @Override
    public LoginVO doLogin(LoginDTO login) throws AppException {
        // 登陆账号不是 email 就是 username，其中 email 优先
        String account = Optional.ofNullable(login.getEmail()).orElse(login.getUsername());

        User u = userMapper.selectOneByQuery(QueryWrapper.create()
                .where(UserTableDef.USER.EMAIL.eq(account))
                .or(UserTableDef.USER.USERNAME.eq(account)));

        if (null == u) {
            throw new AppException(Status.USER_NOT_FOUND);
        }

        // 判断密码
        if (!SaSecureUtil.aesDecrypt(secretKey, u.getPassword()).equals(login.getPassword())) {
            // 失败
            throw new AppException(Status.USERNAME_OR_PASSWD_ERR);
        }

        // 重新查询用户信息
        u = userMapper.selectOneByQuery(QueryWrapper.create()
                .where(UserTableDef.USER.EMAIL.eq(account))
                .or(UserTableDef.USER.USERNAME.eq(account)));

        // 登陆
        StpUtil.login(u.getEmail(), new SaLoginModel()
                .setExtra("userId", u.getId()));

        return new LoginVO(StpUtil.getTokenValue());
    }

    @Override
    public UserInfoVO doGetInfo(String email) throws AppException {
        User u = userMapper.selectOneByQuery(QueryWrapper.create().where(UserTableDef.USER.EMAIL.eq(email)));

        if (null == u) {
            // 真的是未知错误啊
            throw new AppException(Status.ERROR);
        }

        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(u, vo);

        return vo;
    }

    @Override
    public UserInfoVO doUpdateInfo(UserInfoDTO userInfo) throws AppException {
        User u = userMapper.selectOneById(userInfo.getId());

        if (null == u) {
            // 真的是未知错误啊
            throw new AppException(Status.ERROR);
        }

        BeanUtils.copyProperties(userInfo, u);
        u.setId(new BigInteger(userInfo.getId()));
        userMapper.update(u);

        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(u, vo);
        return vo;
    }

    @Override
    public String doResetPassword(ResetPasswdDTO resetPasswd) throws AppException {
        User u = userMapper.selectOneByQuery(QueryWrapper.create().where(UserTableDef.USER.EMAIL.eq(StpUtil.getLoginIdAsString())));

        if (u == null) {
            // 真的是未知错误啊
            throw new AppException(Status.ERROR);
        }

        SaSession session = SaSessionCustomUtil.getSessionById("verCode-" + StpUtil.getLoginIdAsString());

        Integer verCode = session.getInt(StpUtil.getLoginIdAsString());

        if (!resetPasswd.getVerCode().equals(verCode)) {
            log.warn("验证码匹配错误 -- 输入： {}  应是： {}", resetPasswd.getVerCode(), verCode);
            throw new AppException(Status.VERIFY_CODE_NOT_EQ);
        }

        u.setPassword(SaSecureUtil.aesEncrypt(secretKey, resetPasswd.getNewPassword()));
        userMapper.update(u);

        SaSessionCustomUtil.deleteSessionById("verCode-" + StpUtil.getLoginIdAsString());
        return Status.SUCCESS.getMessage();
    }
}
