package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.entity.ProductCategory;
import top.wpaint.marketplus.mapper.ProductCategoryMapper;
import top.wpaint.marketplus.service.ProductCategoryService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

}
