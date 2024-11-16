package top.wpaint.marketplus.config;

import cn.dev33.satoken.jwt.*;
//import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfigure {
    @Bean
    public StpLogic getStpLogicJwt() {
        /**
         * 简单模式 - 对 sa-token 来说，支持的功能最全
         */
        return new StpLogicJwtForSimple();
    }
}