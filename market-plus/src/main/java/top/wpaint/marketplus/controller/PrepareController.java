package top.wpaint.marketplus.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.util.PrepareDB;

@Slf4j
@RestController
@RequestMapping("/api/prepare")
public class PrepareController extends BaseController {

    @Resource
    private PrepareDB prepareDB;

    @GetMapping
    public Result<String> prepare() {
        log.info("准备 Role 数据");
        return prepareDB.prepareRole();
    }
}
