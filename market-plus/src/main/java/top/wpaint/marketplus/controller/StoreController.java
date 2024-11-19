package top.wpaint.marketplus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.extern.slf4j.Slf4j;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.entity.dto.StoreDTO;
import top.wpaint.marketplus.entity.vo.StoreVO;
import top.wpaint.marketplus.service.StoreService;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/store")
public class StoreController {
    
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * 开一个新店
     * @return 结果
     */
    @PutMapping
    public Result<StoreVO> openNewStore(@RequestBody StoreDTO body) throws AppException {
        log.debug("add new store -- {}", body);
        return Result.success(storeService.doOpenNewStore(body));
    }
}
