package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IOrderService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:11
 * @Version: 1.0
 */
public interface IOrderService extends IService<Order> {
    int getOrderSize(String openid);

    List<Order> getOrderAll(String openid, int orderStatus, int page, int size);

    Map<String,Object> getOrderById(String openid);
}
