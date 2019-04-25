package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Order;

import java.util.List;

/**
 * @ClassName: OrderMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:00
 * @Version: 1.0
 */
public interface OrderMapper extends BaseMapper<Order> {
    int getOrderSize(String openid);

    List<Order> getOrderAll(String openid, int orderStatus,int page,int size);

    List<Order> getOrderById(String openid);
}
