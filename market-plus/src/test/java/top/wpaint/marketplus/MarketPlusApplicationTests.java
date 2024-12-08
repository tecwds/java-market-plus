package top.wpaint.marketplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarketPlusApplicationTests {

    @Test
    void contextLoads() {}

//    @Test
    void roleDataGenTest() {
//        Role role = Role.builder()
//            .roleId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
//            .roleName("R_USER")
//            .description("普通用户")
//            .isEnable(LogicConst.ENABLE)
//            .build();
//        roleService.save(role);
    }

    @Test
    void testM2M() {}

//    @Test
    void userDataGenTest() {
//        User user = User.builder()
//            .userId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
//        .email("tecwds@163.com")
//        .username("潘")
////        .rule(new BigInteger(String.valueOf(1297276825285689344L)))
//        .roleName(RoleConst.R_USER)
//        .gender(GenderConst.MALE)
//        .authType(AuthConst.AUTH_EMAIL)
//        .isEnable(LogicConst.ENABLE)
//        .build();
//
//        userService.save(user);
    }

//    @Test
    void authDataGenTest() {
//        Auth auth = Auth.builder()
//            .authId(new BigInteger(String.valueOf(new SnowflakeDistributeIdUtil(0, 0).nextId())))
//        .authName("邮箱")
//        .authType(AuthConst.AUTH_EMAIL)
//        .description("邮箱认证")
//        .isEnable(LogicConst.ENABLE)
//        .build();
//
//        authService.save(auth);
    }

//    @Test
    void userAuthDataGenTest() {
//        UserAuth userAuth = UserAuth.builder()
//            .userId(new BigInteger("1297320546689613824"))
//            .accessKey("tecwds@163.com")
//            .secretKey("test1123@wpan")
//            .description("163邮箱登陆")
//            .authName(AuthConst.A_EMAIL_NAME)
//            .authType(AuthConst.AUTH_EMAIL)
//            .isEnable(LogicConst.ENABLE)
//            .build();
//        userAuthService.save(userAuth);
    }

}
