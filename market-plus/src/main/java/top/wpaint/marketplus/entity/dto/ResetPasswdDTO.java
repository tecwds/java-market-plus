package top.wpaint.marketplus.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswdDTO implements Serializable {
    private Integer verCode;
    private String newPassword;
    private String rePassword;
}
