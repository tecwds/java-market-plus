package top.wpaint.marketplus.service;

import java.util.List;

import com.mybatisflex.core.service.IService;

import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Product;
import top.wpaint.marketplus.entity.dto.ProductDTO;
import top.wpaint.marketplus.entity.vo.ProductVO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-19
 */
public interface ProductService extends IService<Product> {

    ProductVO doPutProduct(ProductDTO product) throws AppException;

    List<ProductVO> doPutBatchProduct(List<ProductDTO> prodcuts) throws AppException;

}
