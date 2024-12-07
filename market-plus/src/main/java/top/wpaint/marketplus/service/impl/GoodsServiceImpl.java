package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.dto.GoodsDTO;
import top.wpaint.marketplus.mapper.GoodsMapper;
import top.wpaint.marketplus.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Integer doAddGoodsBatch(List<GoodsDTO> goodsList) {
        List<Goods> goods = new ArrayList<>(goodsList.size());
        goodsList.forEach(it -> goods.add(Goods.builder()
                .name(it.getName())
                .description(it.getDescription())
                .image(it.getImage())
                .price(it.getPrice())
                .isEnabled(LogicConst.ENABLE)
                .build()));

        int rows = getMapper().insertBatch(goods);

        return rows;
    }
}
