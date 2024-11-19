package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;
}
