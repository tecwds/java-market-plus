package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.dto.UserPasswdDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-10-19
 */
public interface UserService extends IService<User> {

    void doGetVerifyCode(String email) throws AppException;

    void doRegister(RegisterDTO register) throws AppException;

    LoginVO doLogin(LoginDTO login) throws AppException;

    UserInfoVO doGetInfo(String email) throws AppException;

    UserInfoVO doUpdateInfo(UserInfoDTO userInfo) throws AppException;
}
