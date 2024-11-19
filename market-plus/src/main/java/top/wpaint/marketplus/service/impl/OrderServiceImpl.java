package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.entity.Order;
import top.wpaint.marketplus.mapper.OrderMapper;
import top.wpaint.marketplus.service.OrderService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
