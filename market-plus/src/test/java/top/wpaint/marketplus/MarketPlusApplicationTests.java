package top.wpaint.marketplus;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Role;
import top.wpaint.marketplus.service.RoleService;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;

@SpringBootTest
class MarketPlusApplicationTests {

    @Resource
    private RoleService service;

    @Test
    void contextLoads() {
    }

    @Test
    void roleDataGenTest() {
        Role role = Role.builder()
            .roleId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
            .roleName("R_USER")
            .description("普通用户")
            .isEnable(LogicConst.ENABLE)
            .build();
        service.save(role);
    }

}
