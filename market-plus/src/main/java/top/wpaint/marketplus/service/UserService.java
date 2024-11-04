package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.UserInfoDTO;
import top.wpaint.marketplus.entity.dto.UserPasswdDTO;
import top.wpaint.marketplus.entity.vo.UserInfoVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-10-19
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户 ID 获得用户信息
     */
    UserInfoVO doGetUserInfo(String userId) throws AppException;

    UserInfoVO doUpdateUserInfo(String userId, UserInfoDTO body) throws AppException;

    String doUpdateUserPasswd(String userId, UserPasswdDTO body) throws AppException;
}
