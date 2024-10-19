package top.wpaint.marketplus.service.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.mapper.UserAuthMapper;

@Slf4j
@Component
public class UserAuthServiceSupport {

    private final UserAuthMapper userAuthMapper;

    public UserAuthServiceSupport(UserAuthMapper userAuthMapper) {
        this.userAuthMapper = userAuthMapper;
    }

    /**
     * 邮箱登陆
     */
    public LoginVO emailLogin(LoginDTO body, UserAuth userAuth) {
        log.info("邮箱登陆 - {}", body);

        // 发送邮箱验证码




        return null;
    }
}