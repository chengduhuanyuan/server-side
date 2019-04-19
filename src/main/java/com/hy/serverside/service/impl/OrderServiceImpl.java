package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Order;
import com.hy.serverside.mapper.OrderMapper;
import com.hy.serverside.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:17
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
