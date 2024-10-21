package top.wpaint.marketplus.service.support;

import com.mybatisflex.core.query.QueryChain;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.common.ResponseStatus;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.table.UserTableDef;
import top.wpaint.marketplus.mapper.UserMapper;

@Component
public class UserServiceSupport {

    private final UserMapper userMapper;

    public UserServiceSupport(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(String userId) throws AppException {
        User user = QueryChain.of(userMapper)
            .select(UserTableDef.USER.DEFAULT_COLUMNS)
            .from(UserTableDef.USER)
            .where(UserTableDef.USER.USER_ID.eq(userId))
            .one();

        if (null == user) {
            throw new AppException(ResponseStatus.USER_NOT_FOUND);
        }

        return user;
    }

}