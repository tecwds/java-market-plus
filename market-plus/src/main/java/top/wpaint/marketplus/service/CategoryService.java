package top.wpaint.marketplus.service;

import java.util.List;

import com.mybatisflex.core.service.IService;

import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Category;
import top.wpaint.marketplus.entity.dto.CategoryDTO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-16
 */
public interface CategoryService extends IService<Category> {

    Result<List<Category>> doListCategory();

    Result<String> doAddCategory(List<CategoryDTO> body) throws AppException;

}
