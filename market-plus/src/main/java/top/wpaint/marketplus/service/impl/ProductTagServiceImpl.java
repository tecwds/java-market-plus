package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.wpaint.marketplus.entity.ProductTag;
import top.wpaint.marketplus.mapper.ProductTagMapper;
import top.wpaint.marketplus.service.ProductTagService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class ProductTagServiceImpl extends ServiceImpl<ProductTagMapper, ProductTag> implements ProductTagService {

}
