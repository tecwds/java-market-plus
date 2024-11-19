package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;

import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Product;
import top.wpaint.marketplus.entity.dto.ProductDTO;
import top.wpaint.marketplus.entity.vo.ProductVO;
import top.wpaint.marketplus.mapper.ProductMapper;
import top.wpaint.marketplus.service.ProductService;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-11-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final SnowflakeDistributeIdUtil snowUtil;
    private final ProductMapper productMapper;

    public ProductServiceImpl(SnowflakeDistributeIdUtil snowUtil,
            ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.snowUtil = snowUtil;
    }

    @Override
    public ProductVO doPutProduct(ProductDTO product) throws AppException {
        Product pd = new Product();
        // 拷贝相同属性
        BeanUtils.copyProperties(product, pd);
        pd.setProductId(BigInteger.valueOf(snowUtil.nextId()));

        int insert = productMapper.insert(pd);

        if (1 != insert) {
            throw new AppException(Status.ERROR);
        }

        return (ProductVO) pd;
    }

    @Override
    // 事务，抛出错误时 Springboot 会自动回滚事务
    @Transactional
    public List<ProductVO> doPutBatchProduct(List<ProductDTO> prodcuts) throws AppException {
        List<Product> pds = prodcuts.stream()
                .map(it -> {
                    Product pd = new Product();
                    BeanUtils.copyProperties(it, pd);
                    pd.setProductId(BigInteger.valueOf(snowUtil.nextId()));
                    pd.setIsEnable(LogicConst.ENABLE);
                    return pd;
                })
                .collect(Collectors.toList());

        int insert = productMapper.insertBatch(pds);

        if (insert != prodcuts.size()) {
            throw new AppException(Status.ERROR);
        }
        return pds.stream().map(it -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
