package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Store;
import top.wpaint.marketplus.entity.dto.OpenStoreDTO;
import top.wpaint.marketplus.mapper.StoreMapper;
import top.wpaint.marketplus.service.StoreService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public String doOpenStore(BigInteger userId, OpenStoreDTO store) {
        Store s = Store.builder()
                .userId(userId)
                .name(store.getName())
                .description(store.getDescription())
                .isEnabled(LogicConst.ENABLE)
                .build();

        // 添加数据
        this.save(s);

        return Status.SUCCESS.getMessage();
    }
}
