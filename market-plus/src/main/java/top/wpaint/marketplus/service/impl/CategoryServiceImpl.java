package top.wpaint.marketplus.service.impl;

import com.jfinal.template.stat.ast.Stat;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.Category;
import top.wpaint.marketplus.entity.dto.CategoryDTO;
import top.wpaint.marketplus.entity.table.CategoryTableDef;
import top.wpaint.marketplus.entity.vo.CategoryVO;
import top.wpaint.marketplus.mapper.CategoryMapper;
import top.wpaint.marketplus.service.CategoryService;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-11-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final SnowflakeDistributeIdUtil snowUtils;

    public CategoryServiceImpl(CategoryMapper categoryMapper, SnowflakeDistributeIdUtil snowUtils) {
        this.categoryMapper = categoryMapper;
        this.snowUtils = snowUtils;
    }

    @Override
    public List<Category> doListCategory() {
        return QueryChain.of(categoryMapper)
                .select(CategoryTableDef.CATEGORY.DEFAULT_COLUMNS)
                .from(CategoryTableDef.CATEGORY)
                .list();
    }

    @Override
    public String doAddCategory(List<CategoryDTO> body) throws AppException {
        // 检查标签是否存在过
//        Map<String, Category> collect = doListCategory().stream()
//                .collect(Collectors.toMap(Category::getName, Function.identity()));
//        ;
//        List<Category> batch = new ArrayList<>();
//
//        body.stream().filter(item -> !collect.containsKey(item.getName())).forEach(item -> {
//            batch.add(Category.builder()
//                    .categoryId(BigInteger.valueOf(snowUtils.nextId()))
//                    .name(item.getName())
//                    .description(item.getDescription())
//                    .isEnable(LogicConst.ENABLE)
//                    .build());
//        });
//
//        categoryMapper.insertBatch(batch);
        return Status.SUCCESS.getMessage();
    }

    @Override
    public List<CategoryVO> doGetCategoryList() {
        List<Category> categories = categoryMapper.selectAll();

        List<CategoryVO> voList = new ArrayList<>(categories.size());

        categories.forEach(it -> voList.add(new CategoryVO(
                it.getId(),
                it.getName(),
                it.getDescription())));

        return voList;
    }

    @Override
    public String doAddCategoryBatch(List<CategoryDTO> categories) {
        List<Category> categoryList = new ArrayList<>(categories.size());

        categories.forEach(it -> categoryList.add(new Category(
                null,
                it.getName(),
                it.getDescription(),
                null,
                null,
                null,
                LogicConst.ENABLE)));

        categoryMapper.insertBatch(categoryList);
        return Status.SUCCESS.getMessage();
    }

    @Override
    public String doUpdateCategoryBatch(List<CategoryDTO> categories) {
        List<Category> categoryList = new ArrayList<>(categories.size());

        categories.forEach(it -> categoryList.add(Category.builder()
                .id(new BigInteger(it.getId()))
                .name(it.getName())
                .description(it.getDescription())
                .build()
        ));

        this.updateBatch(categoryList);
        return Status.SUCCESS.getMessage();
    }

}
