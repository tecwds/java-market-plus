package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wpaint.marketplus.controller.BaseController;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user/cart")
public class CartController extends BaseController {
}
