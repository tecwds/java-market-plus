package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.Order;
import top.wpaint.marketplus.entity.dto.OrderDTO;
import top.wpaint.marketplus.entity.vo.OrderVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-19
 */
public interface OrderService extends IService<Order> {

    void doAddOrder(OrderDTO order) throws AppException;

    List<OrderVO> doGetOrderList() throws AppException;
}
