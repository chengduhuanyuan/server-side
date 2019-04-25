package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Address;

/**
 * @ClassName: IAddressService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/25 10:16
 * @Version: 1.0
 */
public interface IAddressService extends IService<Address> {
    void updateDefault(String id,String openid);
}
