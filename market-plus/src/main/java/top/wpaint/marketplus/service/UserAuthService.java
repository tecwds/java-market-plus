package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.entity.dto.LoginDTO;
import top.wpaint.marketplus.entity.dto.RegisterDTO;
import top.wpaint.marketplus.entity.dto.VerifyCodeDTO;
import top.wpaint.marketplus.entity.vo.LoginVO;
import top.wpaint.marketplus.entity.vo.VerifyCodeVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-10-19
 */
public interface UserAuthService extends IService<UserAuth> {

    LoginVO doLogin(LoginDTO body) throws AppException;

    VerifyCodeVO doGetVerifyCode(VerifyCodeDTO verifyCode) throws AppException;

    String doRegister(RegisterDTO body) throws AppException;
}
