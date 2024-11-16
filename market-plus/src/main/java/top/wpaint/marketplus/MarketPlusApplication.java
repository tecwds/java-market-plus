package top.wpaint.marketplus;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("top.wpaint.marketplus.mapper")
public class MarketPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketPlusApplication.class, args);
        log.info("潘的大超市正在运行...");
        log.info("潘的黑市正在悄悄运行<-<");
    }
}
