package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.entity.Address;
import top.wpaint.marketplus.entity.dto.AddressDTO;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-12-05
 */
public interface AddressService extends IService<Address> {

    void doAddAddress(AddressDTO address);

    Address doUpdateAddress(AddressDTO address);
}
