package top.wpaint.marketplus;

import com.mybatisflex.core.query.QueryChain;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.wpaint.marketplus.common.constant.AuthConst;
import top.wpaint.marketplus.common.constant.GenderConst;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.constant.RoleConst;
import top.wpaint.marketplus.entity.Auth;
import top.wpaint.marketplus.entity.Role;
import top.wpaint.marketplus.entity.User;
import top.wpaint.marketplus.entity.UserAuth;
import top.wpaint.marketplus.service.AuthService;
import top.wpaint.marketplus.service.RoleService;
import top.wpaint.marketplus.service.UserAuthService;
import top.wpaint.marketplus.service.UserService;
import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

import java.math.BigInteger;

@SpringBootTest
class MarketPlusApplicationTests {

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Resource
    private AuthService authService;

    @Resource
    private UserAuthService userAuthService;

    @Test
    void contextLoads() {
    }

//    @Test
    void roleDataGenTest() {
        Role role = Role.builder()
            .roleId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
            .roleName("R_USER")
            .description("普通用户")
            .isEnable(LogicConst.ENABLE)
            .build();
        roleService.save(role);
    }

//    @Test
    void userDataGenTest() {
        User user = User.builder()
            .userId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
        .username("tecwds@163.com")
        .nickname("潘")
//        .rule(new BigInteger(String.valueOf(1297276825285689344L)))
        .roleName(RoleConst.R_USER)
        .gender(GenderConst.MALE)
        .authType(AuthConst.AUTH_EMAIL)
        .isEnable(LogicConst.ENABLE)
        .build();

        userService.save(user);
    }

//    @Test
    void authDataGenTest() {
        Auth auth = Auth.builder()
            .authId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
        .authName("邮箱")
        .authType(AuthConst.AUTH_EMAIL)
        .description("邮箱认证")
        .isEnable(LogicConst.ENABLE)
        .build();

        authService.save(auth);
    }

//    @Test
    void userAuthDataGenTest() {
        UserAuth userAuth = UserAuth.builder()
            .authId(new BigInteger("1297319908631117824"))
            .userId(new BigInteger("1297320546689613824"))
            .username("tecwds@163.com")
            .accessKey("tecwds@163.com")
            .secretKey("test1123@wpan")
            .description("163邮箱登陆")
            .authName(AuthConst.A_EMAIL_NAME)
            .authType(AuthConst.AUTH_EMAIL)
            .isEnable(LogicConst.ENABLE)
            .build();
        userAuthService.save(userAuth);
    }

}
