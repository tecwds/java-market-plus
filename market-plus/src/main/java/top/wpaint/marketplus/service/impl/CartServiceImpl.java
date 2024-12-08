package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Cart;
import top.wpaint.marketplus.entity.table.CartTableDef;
import top.wpaint.marketplus.entity.table.GoodsTableDef;
import top.wpaint.marketplus.entity.vo.CartInfoVO;
import top.wpaint.marketplus.mapper.CartMapper;
import top.wpaint.marketplus.mapper.GoodsMapper;
import top.wpaint.marketplus.service.CartService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Slf4j
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

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

    @Override
    public void doDeleteCartGoods(List<String> ids) {
        // TODO 需要优化
        List<Cart> carts = getMapper().selectListByQuery(QueryWrapper.create()
                .where(CartTableDef.CART.USER_ID.eq(StpUtil.getExtra("userId").toString())));

        List<String> deletes = new ArrayList<>();

        carts.forEach(it -> {
            if (ids.contains(it.getGoodsId().toString())) {
                deletes.add(it.getId().toString());
            }
        });

        removeByIds(deletes);
    }

    @Override
    public void doUpdateCartNums(String goodsId, String count) {
        Cart cart = getMapper().selectOneByQuery(QueryWrapper.create().
                where(CartTableDef.CART.GOODS_ID.eq(goodsId))
                .and(CartTableDef.CART.USER_ID.eq(StpUtil.getExtra("userId").toString())));

        cart.setCount(Long.parseLong(count));

        updateById(cart);
    }
}
