package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;

import cn.dev33.satoken.stp.StpUtil;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.constant.RoleConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Store;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.dto.StoreDTO;
import top.wpaint.marketplus.entity.vo.StoreVO;
import top.wpaint.marketplus.mapper.StoreMapper;
import top.wpaint.marketplus.mapper.UserMapper;
import top.wpaint.marketplus.service.StoreService;
import top.wpaint.marketplus.service.support.UserServiceSupport;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    private final UserServiceSupport userServiceSupport;
    private final SnowflakeDistributeIdUtil snowUtil;
    private final StoreMapper storeMapper;
    private final UserMapper userMapper;

    public StoreServiceImpl(UserServiceSupport userServiceSupport,
            SnowflakeDistributeIdUtil snowUtil,
            StoreMapper storeMapper,
            UserMapper userMapper) {
        this.userServiceSupport = userServiceSupport;
        this.snowUtil = snowUtil;
        this.storeMapper = storeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public StoreVO doOpenNewStore(StoreDTO body) throws AppException {
        // 获得当前用户 ID
        String userId = (String) StpUtil.getLoginId();

        User user = userServiceSupport.getUserById(userId);

        if (!user.getRoleName().equals(RoleConst.R_SELLER)) {
            // 申请开店，开店用户成为商家
            user.setRoleName(RoleConst.R_SELLER);
            // do update 更新用户状态
            userMapper.update(user);
        }

        Store store = Store.builder()
                .storeId(BigInteger.valueOf(snowUtil.nextId()))
                .userId(BigInteger.valueOf(Long.parseLong(userId)))
                .isEnable(LogicConst.NOT_DELETED)
                .name(body.getName())
                .description(body.getDescription())
                .isEnable(LogicConst.ENABLE)
                .build();

        int insert = storeMapper.insert(store);;

        if (1 != insert) {
            throw new AppException(Status.ERROR);
        }

        StoreVO vo = new StoreVO();
        BeanUtils.copyProperties(store, vo);
        return vo;
    }

    @Override
    public List<StoreVO> doUpdateStoreBatch(List<StoreVO> body) throws AppException {
        // TODO add new state
        throw new AppException(400, "未实现");
    }

}
