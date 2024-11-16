package top.wpaint.marketplus.service.support;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;

@Slf4j
@Component
public class UserAuthServiceSupport {

    /**
     * 邮箱登陆
     */
    public LoginVO emailLogin(LoginDTO body, UserAuth userAuth) throws AppException {
        log.debug("邮箱登陆 - {}", body);

        if (body.getSecretKey().equals(userAuth.getSecretKey())) {
            log.debug("登陆成功 - {}", body);
            StpUtil.login(userAuth.getUserId().toString());
            return new LoginVO(StpUtil.getTokenValue());
        }

        throw new AppException(Status.USERNAME_OR_PASSWD_ERR);
    }
}