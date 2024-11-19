package top.wpaint.marketplus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.dto.ProductDTO;
import top.wpaint.marketplus.entity.vo.ProductVO;
import top.wpaint.marketplus.service.ProductService;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 添加新的商品
     */
    @PutMapping
    public Result<ProductVO> putProduct(@RequestBody ProductDTO body) throws AppException {
        log.debug("添加新的商品 -- {}", body);
        return productService.doPutProduct(body);
    }

    /**
     * 批量添加新的商品
     */
    @PutMapping
    public Result<List<ProductVO>> putBatchProduct(@RequestBody List<ProductDTO> body) throws AppException {
        log.debug("批量添加新的商品 -- 数量为 {}", body.size());
        return productService.doPutBatchProduct(body);
    }
}
