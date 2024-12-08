package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.dto.GoodsDTO;
import top.wpaint.marketplus.entity.vo.GoodsTagCategoryVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-12-05
 */
public interface GoodsService extends IService<Goods> {

    Integer doAddGoodsBatch(List<GoodsDTO> goodsList) throws AppException;

    GoodsTagCategoryVO doGetTagCategory(String goodsId);
}
