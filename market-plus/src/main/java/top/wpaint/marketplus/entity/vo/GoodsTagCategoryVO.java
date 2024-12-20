package top.wpaint.marketplus.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.wpaint.marketplus.entity.Category;
import top.wpaint.marketplus.entity.Tag;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsTagCategoryVO {
    private BigInteger goodsId;

    private List<Tag> tags;
    private List<Category> categories;
}
