package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.Order;
import top.wpaint.marketplus.entity.dto.OrderDTO;
import top.wpaint.marketplus.entity.support.OrderGoods;
import top.wpaint.marketplus.entity.table.OrderTableDef;
import top.wpaint.marketplus.entity.vo.GoodsVO;
import top.wpaint.marketplus.entity.vo.OrderVO;
import top.wpaint.marketplus.mapper.GoodsMapper;
import top.wpaint.marketplus.mapper.OrderMapper;
import top.wpaint.marketplus.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 服务层实现。
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
        // 个人所有订单
        List<Order> orders = list(QueryWrapper.create()
                .where(OrderTableDef.ORDER.USER_ID.eq(StpUtil.getExtra("useId").toString())));

        // 订单 + 商品信息
        List<OrderVO> voList = new ArrayList<>();

        Set<String> goodsIDs = new HashSet<>();
        orders.forEach(it -> goodsIDs.add(it.getGoodsId().toString()));

        // 获得订单 map, 方便后续赋值
        Map<BigInteger, Goods> abouts = goodsMapper.selectListByIds(goodsIDs)
                .stream()
                .collect(Collectors.toMap(Goods::getId, it -> it));

        // 订单ID，订单
        orders.stream()
                .collect(Collectors.groupingBy(Order::getOrderId))
                .forEach((key, value) -> {
                    List<GoodsVO> goodsVOS = value.stream().map(it -> {
                        // 单个商品
                        GoodsVO vo = new GoodsVO();
                        vo.setId(it.getGoodsId());
                        vo.setName(abouts.get(it.getGoodsId()).getName());
                        vo.setDescription(abouts.get(it.getGoodsId()).getDescription());
                        vo.setImage(abouts.get(it.getGoodsId()).getImage());
                        vo.setPrice(it.getPrice());
                        vo.setCount(it.getCount());
                        vo.setTotal(it.getPrice().multiply(BigInteger.valueOf(it.getCount())));
                        return vo;
                    }).toList();
                    voList.add(new OrderVO(key, goodsVOS));
                });

        return voList;

    }
}
