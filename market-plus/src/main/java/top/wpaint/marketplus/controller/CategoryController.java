package top.wpaint.marketplus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Category;
import top.wpaint.marketplus.entity.dto.CategoryDTO;
import top.wpaint.marketplus.service.CategoryService;


@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 查询分类信息
     * 
     * @return List<Category>
     */
    @GetMapping("list")
    // @SaCheckLogin
    public Result<List<Category>> listCategory() {
        // log.debug("分类查看测试 - {}", StpUtil.getLoginIdAsString());
        return Result.success(categoryService.doListCategory());
    }

    /**
     * 添加新的分类
     * @param body
     * @return Result<String>
     * @throws AppException
     */
    @PutMapping
    public Result<String> addCategory(@RequestBody List<CategoryDTO> body) throws AppException {
        log.debug("测试 - 添加 category -- {}", body);
        return Result.success(categoryService.doAddCategory(body));
    }

    
    public Result<String> delCategoryBatch() {
        return null;
    }
    
    
}
