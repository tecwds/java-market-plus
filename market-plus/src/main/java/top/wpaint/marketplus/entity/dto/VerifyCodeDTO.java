package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名（邮箱）
     */
    private String email;
}