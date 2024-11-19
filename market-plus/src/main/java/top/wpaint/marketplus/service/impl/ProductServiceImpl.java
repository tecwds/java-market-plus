package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.entity.Product;
import top.wpaint.marketplus.mapper.ProductMapper;
import top.wpaint.marketplus.service.ProductService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
