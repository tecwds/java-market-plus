package top.wpaint.marketplus.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/healthy")
public class HealthController {

    @GetMapping
    public String checkHealthy() {
        JSONObject entries = new JSONObject();
        entries.set("status", "UP");
        return entries.toString();
    }
}
