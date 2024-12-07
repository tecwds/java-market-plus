package top.wpaint.marketplus.controller.store;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.dto.GoodsDTO;
import top.wpaint.marketplus.entity.vo.GoodsVO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/store/goods")
public class GoodsController extends BaseController {

    @SaIgnore
    @GetMapping("list")
    public Result<List<GoodsVO>> getAllGoods() {
        log.info("获得所有商品信息");
        List<Goods> goods = goodsService.list();
        List<GoodsVO> voList = new ArrayList<>(goods.size());
        goods.forEach(it -> voList.add(GoodsVO.builder()
                .id(it.getId())
                .image(it.getImage())
                .description(it.getDescription())
                .name(it.getName())
                .price(it.getPrice())
                .build()));

        return Result.success(voList);
    }

    @SaIgnore
    @GetMapping("info")
    public Result<GoodsVO> getGoodsInfo(String goodsId) {
        log.info("获得 {} 商品信息", goodsId);
        GoodsVO vo = new GoodsVO();
        Goods goods = goodsService.getById(goodsId);
        BeanUtils.copyProperties(goods, vo);
        return Result.success(vo);
    }

    @PutMapping("batch")
    public Result<Integer> addGoodsBatch(@RequestBody List<GoodsDTO> goodsList) throws AppException {
        log.info("批量添加商品，数量为： {}", goodsList.size());
        return Result.success(goodsService.doAddGoodsBatch(goodsList));
    }
}
