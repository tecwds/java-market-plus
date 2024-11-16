package top.wpaint.marketplus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckLogin;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.entity.Category;

@RestController("/api/category/")
public class CategoryController {

    @GetMapping("list")
    @SaCheckLogin
    public Result<List<Category>> listCategory() {
        
        return Result.error();
    }
}
