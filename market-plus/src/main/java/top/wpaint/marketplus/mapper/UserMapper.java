package top.wpaint.marketplus.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.wpaint.marketplus.entity.User;

/**
 *  映射层。
 *
 * @author tecwds
 * @since 2024-12-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
