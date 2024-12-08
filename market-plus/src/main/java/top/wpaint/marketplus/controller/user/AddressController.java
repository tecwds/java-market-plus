package top.wpaint.marketplus.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.dto.AddressDTO;
import top.wpaint.marketplus.entity.table.AddressTableDef;
import top.wpaint.marketplus.service.AddressService;

import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/user/address")
public class AddressController extends BaseController {

    @GetMapping("list")
    public Result<List<Address>> getAddressList() {
        log.info("用户 {} 获得所有收货地址", StpUtil.getLoginIdAsString());
        return Result.success(addressService.list(QueryWrapper.create()
                .where(AddressTableDef.ADDRESS.USER_ID.eq(StpUtil.getExtra("userId").toString()))));
    }

    @PutMapping
    public Result<String> addAddress(@RequestBody AddressDTO address) {
        log.info("用户 {} 新增地址： {}", StpUtil.getLoginIdAsString(), address);
        addressService.doAddAddress(address);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteAddress(String addressId) {
        log.info("用户 {} 删除地址： {}", StpUtil.getLoginIdAsString(), addressId);
        addressService.removeById(addressId);
        return Result.success();
    }

    @PostMapping
    public Result<Address> updateAddress(@RequestBody AddressDTO address) {
        log.info("用户 {} 修改地址： {}", StpUtil.getLoginIdAsString(), address);
        return Result.success(addressService.doUpdateAddress(address));
    }
}
