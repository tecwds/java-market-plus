package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.*;
import top.wpaint.marketplus.entity.dto.GoodsDTO;
import top.wpaint.marketplus.entity.table.GoodsCategoryTableDef;
import top.wpaint.marketplus.entity.table.GoodsTagTableDef;
import top.wpaint.marketplus.entity.table.TagTableDef;
import top.wpaint.marketplus.entity.vo.GoodsTagCategoryVO;
import top.wpaint.marketplus.mapper.*;
import top.wpaint.marketplus.service.GoodsService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    @Resource
    private TagMapper tagMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private GoodsTagMapper goodsTagMapper;

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public Integer doAddGoodsBatch(List<GoodsDTO> goodsList) throws AppException {
        List<Goods> goods = new ArrayList<>(goodsList.size());
        goodsList.forEach(it -> goods.add(Goods.builder()
                .name(it.getName())
                .description(it.getDescription())
                .image(it.getImage())
                .price(it.getPrice())
                .isEnabled(LogicConst.ENABLE)
                .build()));


        // 商店信息
        Store store = storeMapper.selectOneById(StpUtil.getExtra("userId").toString());

        if (null == store) {
            throw new AppException(Status.USER_NOT_SELLER);
        }

        // 库存
        List<Inventory> invList = goods.stream().map(item -> Inventory.builder()
                .count(0L)
                .goodsId(item.getId())
                .price(item.getPrice())
                .isEnabled(LogicConst.ENABLE)
                .storeId(store.getId())
                .build()
        ).toList();

        // 库存
        inventoryMapper.insertBatch(invList);

        // 商品
        return getMapper().insertBatch(goods);
    }

    @Override
    public GoodsTagCategoryVO doGetTagCategory(String goodsId) {
//        Goods goods = getById(goodsId);
        List<String> tagIds = goodsTagMapper.selectListByQuery(QueryWrapper.create()
                        .where(GoodsTagTableDef.GOODS_TAG.GOODS_ID.eq(goodsId)))
                .stream()
                .map(it -> it.getTagId().toString())
                .toList();

        List<String> categoryIds = goodsCategoryMapper.selectListByQuery(QueryWrapper.create()
                        .where(GoodsCategoryTableDef.GOODS_CATEGORY.GOODS_ID.eq(goodsId)))
                .stream()
                .map(it -> it.getCategoryId().toString())
                .toList();

        GoodsTagCategoryVO vo = new GoodsTagCategoryVO();

        vo.setGoodsId(new BigInteger(goodsId));
        if (!tagIds.isEmpty()) {
            vo.setTags(tagMapper.selectListByIds(tagIds));
        }
        if (!categoryIds.isEmpty()) {
            vo.setCategories(categoryMapper.selectListByIds(categoryIds));
        }
        return vo;
    }
}
