package top.wpaint.marketplus.entity.vo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String token;
}