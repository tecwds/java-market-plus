package top.wpaint.marketplus.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.service.*;
import top.wpaint.marketplus.service.support.UserServiceSupport;

@RestController
public class BaseController {

    @Resource
    protected UserService userService;

    @Resource
    protected UserServiceSupport userServiceSupport;

    @Resource
    protected CategoryService categoryService;

    @Resource
    protected TagService tagService;

    @Resource
    protected StoreService storeService;

    @Resource
    protected GoodsService goodsService;
}
