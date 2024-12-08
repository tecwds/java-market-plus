package top.wpaint.marketplus.controller.store;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.Inventory;
import top.wpaint.marketplus.entity.table.InventoryTableDef;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/store/inventory")
public class InventoryController extends BaseController {

    @GetMapping("list")
    public Result<Long> getGoodsInventory(String goodsId) {
        log.info("获得商品 {} 库存信息", goodsId);
        Inventory inv = inventoryService.getMapper().selectOneByQuery(QueryWrapper.create()
                .where(InventoryTableDef.INVENTORY.GOODS_ID.eq(goodsId)));

        return Result.success(inv.getCount());
    }

    @GetMapping("setInventory")
    public Result<String> setGoodsInventory(String goodsId, Long count) throws AppException {
        log.info("设置商品 {} 库存量为： {}", goodsId, count);
        Inventory inv = inventoryService.getMapper().selectOneByQuery(QueryWrapper.create()
                .where(InventoryTableDef.INVENTORY.GOODS_ID.eq(goodsId)));

        if (null == inv) {
            throw new AppException(Status.INVENTORY_NOT_FOUND);
        }

        inv.setCount(count);
        inventoryService.updateById(inv);
        return Result.success();
    }
}
