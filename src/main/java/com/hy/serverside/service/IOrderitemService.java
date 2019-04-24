package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.entity.ShopCart;

import java.util.List;

/**
 * @ClassName: IOrderitemService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:12
 * @Version: 1.0
 */
public interface IOrderitemService extends IService<Orderitem> {
    List<ShopCart> getShopCart(String openid);
}
