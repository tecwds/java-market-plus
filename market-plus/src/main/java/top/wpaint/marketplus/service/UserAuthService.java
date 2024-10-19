package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-10-19
 */
public interface UserAuthService extends IService<UserAuth> {

    LoginVO doLogin(LoginDTO body);
}
