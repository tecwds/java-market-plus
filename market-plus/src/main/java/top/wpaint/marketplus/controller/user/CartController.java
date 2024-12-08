package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.table.CartTableDef;
import top.wpaint.marketplus.entity.vo.CartInfoVO;
import top.wpaint.marketplus.entity.vo.GoodsVO;

import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user/cart")
public class CartController extends BaseController {

    @GetMapping("list")
    public Result<List<CartInfoVO>> getCartInfo() {
        log.info("用户 {} 查看购物车商品", StpUtil.getLoginIdAsString());
        return Result.success(cartService.doGetCartInfo());
    }

    @DeleteMapping("delete")
    public Result<String> deleteCartGoods(@RequestBody List<String> ids) {
        log.info("删除商品，数量为： {}", ids.size());
        cartService.doDeleteCartGoods(ids);
        return Result.success();
    }

    @GetMapping("update")
    public Result<String> updateCartNums(String goodsId, String count) {
        log.info("更新购物车商品数量 -- 商品 ID： {}，更新后为： {}", goodsId, count);
        cartService.doUpdateCartNums(goodsId, count);
        return Result.success();
    }
}
