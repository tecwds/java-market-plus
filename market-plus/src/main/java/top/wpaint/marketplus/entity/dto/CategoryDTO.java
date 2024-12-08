package top.wpaint.marketplus.entity.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;
}
