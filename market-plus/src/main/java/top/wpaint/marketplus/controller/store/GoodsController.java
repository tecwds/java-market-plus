package top.wpaint.marketplus.controller.store;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.Goods;
import top.wpaint.marketplus.entity.GoodsCategory;
import top.wpaint.marketplus.entity.GoodsTag;
import top.wpaint.marketplus.entity.dto.GoodsDTO;
import top.wpaint.marketplus.entity.table.GoodsCategoryTableDef;
import top.wpaint.marketplus.entity.table.GoodsTagTableDef;
import top.wpaint.marketplus.entity.vo.GoodsTagCategoryVO;
import top.wpaint.marketplus.entity.vo.GoodsVO;

import java.math.BigInteger;
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

    @DeleteMapping("batch")
    public Result<String> deleteGoodsBatch(@RequestBody List<String> ids) {
        log.info("批量删除商品，数量为： {}", ids.size());
        goodsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("tag")
    public Result<String> addTag(String goodsId, String tagId) {
        log.info("商品 {} 添加标签 {}", goodsId, tagId);
        GoodsTag gt = new GoodsTag();
        gt.setGoodsId(new BigInteger(goodsId));
        gt.setTagId(new BigInteger(tagId));
        gt.setIsEnabled(LogicConst.ENABLE);
        goodsTagService.save(gt);
        return Result.success();
    }

    @DeleteMapping("tag")
    public Result<String> deleteTag(String goodsId, String tagId) {
        log.info("商品 {} 删除标签 {}", goodsId, tagId);
        goodsTagService.remove(QueryWrapper.create()
                .where(GoodsTagTableDef.GOODS_TAG.GOODS_ID.eq(goodsId))
                .and(GoodsTagTableDef.GOODS_TAG.TAG_ID.eq(tagId))
        );
        return Result.success();
    }

    @GetMapping("category")
    public Result<String> addCategory(String goodsId, String categoryId) {
        log.info("商品 {} 添加分类 {}", goodsId, categoryId);
        GoodsCategory gc = new GoodsCategory();
        gc.setGoodsId(new BigInteger(goodsId));
        gc.setCategoryId(new BigInteger(categoryId));
        gc.setIsEnabled(LogicConst.ENABLE);
        goodsCategoryService.save(gc);
        return Result.success();
    }

    @DeleteMapping("category")
    public Result<String> deleteCategory(String goodsId, String categoryId) {
        log.info("商品 {} 删除分类 {}", goodsId, categoryId);
        goodsCategoryService.remove(QueryWrapper.create()
                .where(GoodsCategoryTableDef.GOODS_CATEGORY.GOODS_ID.eq(goodsId))
                .and(GoodsCategoryTableDef.GOODS_CATEGORY.CATEGORY_ID.eq(categoryId))
        );
        return Result.success();
    }

    @GetMapping("allCategoryAndTag")
    public Result<GoodsTagCategoryVO> getTagCategory(String goodsId) {
        log.info("获得所有 {} 商品的分类和标签", goodsId);
        return Result.success(goodsService.doGetTagCategory(goodsId));
    }
}
