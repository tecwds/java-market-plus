package top.wpaint.marketplus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.dto.AddressDTO;
import top.wpaint.marketplus.mapper.AddressMapper;
import top.wpaint.marketplus.service.AddressService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 *  服务层实现。
 *
 * @author tecwds
 * @since 2024-12-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public void doAddAddress(AddressDTO address) {
        Address addr = new Address();
        BeanUtils.copyProperties(address, addr);
        addr.setUserId(new BigInteger(StpUtil.getExtra("userId").toString()));
        addr.setEmail(StpUtil.getLoginIdAsString());
        addr.setIsEnabled(LogicConst.ENABLE);
        save(addr);
    }

    @Override
    public Address doUpdateAddress(AddressDTO address) {
        Address addr = getById(address.getId());
        BeanUtils.copyProperties(address, addr);
        updateById(addr);
        return addr;
    }
}
