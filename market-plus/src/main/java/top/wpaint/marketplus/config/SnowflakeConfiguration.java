package top.wpaint.marketplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.wpaint.marketplus.util.SnowflakeDistributeIdUtil;

@Configuration
public class SnowflakeConfiguration {
    
    @Bean
    public SnowflakeDistributeIdUtil getSnowFlake() {
        return new SnowflakeDistributeIdUtil(0, 0);
    }
}
