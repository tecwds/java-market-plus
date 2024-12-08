package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.Order;
import top.wpaint.marketplus.entity.dto.AddressDTO;
import top.wpaint.marketplus.entity.dto.OrderDTO;
import top.wpaint.marketplus.entity.support.OrderGoods;
import top.wpaint.marketplus.entity.table.AddressTableDef;
import top.wpaint.marketplus.entity.table.OrderTableDef;
import top.wpaint.marketplus.entity.vo.OrderVO;

import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user/order")
public class OrderController extends BaseController {

    @GetMapping("list")
    public Result<List<OrderVO>> getOrderList() throws AppException {
        log.info("用户 {} 获得所有订单", StpUtil.getLoginIdAsString());
        return Result.success(orderService.doGetOrderList());
    }

    @PutMapping
    public Result<String> addOrder(@RequestBody OrderDTO order) throws AppException {
        log.info("用户 {} 新增订单： {}", StpUtil.getLoginIdAsString(), order);
        orderService.doAddOrder(order);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteOrder(String orderId) {
        log.info("用户 {} 删除订单： {}", StpUtil.getLoginIdAsString(), orderId);
        orderService.remove(QueryWrapper.create().where(OrderTableDef.ORDER.ORDER_ID.eq(orderId)));
        return Result.success();
    }
}
