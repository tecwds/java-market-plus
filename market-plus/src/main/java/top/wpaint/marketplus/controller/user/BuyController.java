package top.wpaint.marketplus.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.controller.BaseController;

@Slf4j
@RestController
@RequestMapping("/api/user/shopping")
public class BuyController extends BaseController {

    @PutMapping("putCart")
    public Result<String> addGoodsToCart(String goodsId, Long count) {
        log.info("添加 {} 个 {} 商品到购物车", count, goodsId);
        cartService.doAddGoodsToCart(goodsId, count);
        return Result.success();
    }
}
