package top.wpaint.marketplus.controller.category;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.dto.CategoryDTO;
import top.wpaint.marketplus.entity.vo.CategoryVO;

import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    @SaIgnore
    @GetMapping("list")
    public Result<List<CategoryVO>> getCategoryList() {
        log.info("获得所有分类信息");
        return Result.success(categoryService.doGetCategoryList());
    }

    @PutMapping("batch")
    public Result<String> addCategoryBatch(@RequestBody List<CategoryDTO> categories) {
        log.info("批量添加 Category -- {}", categories.size());
        return Result.success(categoryService.doAddCategoryBatch(categories));
    }

    @DeleteMapping("batch")
    public Result<String> deleteCategoryBatch(@RequestBody List<String> ids) {
        log.info("批量删除 Category -- {}", ids.size());
        categoryService.removeByIds(ids);
        return Result.success();
    }

    @PostMapping("batch")
    public Result<String> updateCategoryBatch(@RequestBody List<CategoryDTO> categories) {
        log.info("批量修改 Category -- {}", categories.size());
        return Result.success(categoryService.doUpdateCategoryBatch(categories));
    }
}
