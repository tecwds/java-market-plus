package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.entity.Cart;
import top.wpaint.marketplus.entity.vo.CartInfoVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-12-05
 */
public interface CartService extends IService<Cart> {

    void doAddGoodsToCart(String goodsId, Long count);

    List<CartInfoVO> doGetCartInfo();

    void doDeleteCartGoods(List<String> ids);

    void doUpdateCartNums(String goodsId, String count);
}
