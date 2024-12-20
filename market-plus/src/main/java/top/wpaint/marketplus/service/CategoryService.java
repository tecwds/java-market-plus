package top.wpaint.marketplus.service;

import java.util.List;

import com.mybatisflex.core.service.IService;

import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Category;
import top.wpaint.marketplus.entity.dto.CategoryDTO;
import top.wpaint.marketplus.entity.vo.CategoryVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-16
 */
public interface CategoryService extends IService<Category> {

    List<CategoryVO> doGetCategoryList();

    String doAddCategoryBatch(List<CategoryDTO> categories);

    String doUpdateCategoryBatch(List<CategoryDTO> categories);
}
