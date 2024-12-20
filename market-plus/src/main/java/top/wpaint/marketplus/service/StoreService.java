package top.wpaint.marketplus.service;

import java.math.BigInteger;
import java.util.List;

import com.mybatisflex.core.service.IService;

import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Store;
import top.wpaint.marketplus.entity.dto.OpenStoreDTO;
import top.wpaint.marketplus.entity.dto.StoreDTO;
import top.wpaint.marketplus.entity.vo.StoreVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-19
 */
public interface StoreService extends IService<Store> {

    String doOpenStore(BigInteger userId, OpenStoreDTO store);
}
