package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.mapper.UserMapper;
import top.wpaint.marketplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
