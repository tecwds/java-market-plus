package top.wpaint.marketplus.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/healthy")
public class HealthController {

    @SaIgnore
    @GetMapping
    public String checkHealthy() {
        log.info("执行健康检查");
        return "UP";
    }
}
