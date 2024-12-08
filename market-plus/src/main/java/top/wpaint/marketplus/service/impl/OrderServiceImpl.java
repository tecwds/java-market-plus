package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.Order;
import top.wpaint.marketplus.entity.dto.OrderDTO;
import top.wpaint.marketplus.entity.support.OrderGoods;
import top.wpaint.marketplus.entity.table.OrderTableDef;
import top.wpaint.marketplus.entity.vo.OrderVO;
import top.wpaint.marketplus.mapper.GoodsMapper;
import top.wpaint.marketplus.mapper.OrderMapper;
import top.wpaint.marketplus.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void doAddOrder(OrderDTO order) {
        // TODO 需要优化
        List<String> goodsIds = new ArrayList<>(order.getGoods().size());

        order.getGoods().forEach(it -> goodsIds.add(it.getGoodsId().toString()));

        List<Goods> goodsList = goodsMapper.selectListByIds(goodsIds);

        List<Order> orders = new ArrayList<>(goodsList.size());

        // goodsId, total
        Map<BigInteger, Long> goodsAndTotal = order.getGoods()
                .stream()
                .collect(Collectors.toMap(OrderGoods::getGoodsId, OrderGoods::getCount));

        BigInteger userId = new BigInteger(StpUtil.getExtra("userId").toString());

        for (Goods goods : goodsList) {
            Order o = new Order();
            o.setUserId(userId);
            o.setGoodsId(goods.getId());
            o.setPrice(goods.getPrice());
            o.setCount(goodsAndTotal.get(goods.getId()));
            o.setTotal(goods.getPrice().multiply(BigInteger.valueOf(o.getCount())));
            o.setIsEnabled(LogicConst.ENABLE);
            orders.add(o);
        }

        saveBatch(orders);
    }

    @Override
    public List<OrderVO> doGetOrderList() {
        // TODO 需要优化
        // 获得用户所有订单
//        List<Order> oList = list(QueryWrapper.create()
//                .where(OrderTableDef.ORDER.USER_ID.eq(StpUtil.getExtra("userId").toString())));
//
//        List<BigInteger> goodsIds = new ArrayList<>(oList.size());
//        oList.forEach(it -> goodsIds.add(it.getGoodsId()));
//
//        List<Goods> goods = goodsMapper.selectListByIds(goodsIds);
//
//        List<OrderVO> voList = new ArrayList<>(oList.size());

        return null;

    }
}
