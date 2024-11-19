package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;

import cn.dev33.satoken.stp.StpUtil;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Store;
import top.wpaint.marketplus.entity.dto.StoreDTO;
import top.wpaint.marketplus.entity.vo.StoreVO;
import top.wpaint.marketplus.mapper.StoreMapper;
import top.wpaint.marketplus.service.StoreService;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;

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

    // private final UserServiceSupport userServiceSupport;
    private final SnowflakeDistributeIdUtil snowUtil;
    private final StoreMapper storeMapper;

    public StoreServiceImpl(//UserServiceSupport userServiceSupport,
            SnowflakeDistributeIdUtil snowUtil,
            StoreMapper storeMapper) {
        // this.userServiceSupport = userServiceSupport;
        this.snowUtil = snowUtil;
        this.storeMapper = storeMapper;
    }

    @Override
    public StoreVO doOpenNewStore(StoreDTO body) throws AppException {
        // 获得当前用户 ID
        String userId = (String) StpUtil.getLoginId();

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

}
