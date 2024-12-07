package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Cart;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.table.CartTableDef;
import top.wpaint.marketplus.entity.table.GoodsTableDef;
import top.wpaint.marketplus.entity.vo.CartInfoVO;
import top.wpaint.marketplus.entity.vo.GoodsVO;
import top.wpaint.marketplus.mapper.CartMapper;
import top.wpaint.marketplus.mapper.GoodsMapper;
import top.wpaint.marketplus.service.CartService;
import org.springframework.stereotype.Service;
import top.wpaint.marketplus.service.GoodsService;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Slf4j
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void doAddGoodsToCart(String goodsId, Long count) {
        String userId = StpUtil.getExtra("userId").toString();

        Cart cart = getMapper().selectOneByQuery(QueryWrapper.create()
                .where(CartTableDef.CART.GOODS_ID.eq(goodsId)));

        if (null == cart) {
            cart = Cart.builder()
                    .userId(new BigInteger(userId))
                    .goodsId(new BigInteger(goodsId))
                    .count(count)
                    .isEnabled(LogicConst.ENABLE)
                    .build();
        } else {
            cart.setCount(cart.getCount() + count);
        }
        getMapper().insertOrUpdate(cart);
    }

    @Override
    public List<CartInfoVO> doGetCartInfo() {
        return this.getMapper().selectListByQueryAs(QueryWrapper.create()
                        .from(CartTableDef.CART)
                        .leftJoin(GoodsTableDef.GOODS).on(CartTableDef.CART.GOODS_ID.eq(GoodsTableDef.GOODS.ID))
                        .where(CartTableDef.CART.USER_ID.eq(StpUtil.getExtra("userId").toString())),
                CartInfoVO.class
        );
    }
}
