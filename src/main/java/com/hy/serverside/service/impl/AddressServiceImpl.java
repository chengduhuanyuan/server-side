package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Address;
import com.hy.serverside.mapper.AddressMapper;
import com.hy.serverside.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AddressServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/25 10:17
 * @Version: 1.0
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Override
    public void updateDefault(String id, String openid) {
        baseMapper.setDefaultAddress(id,openid);
    }
}
