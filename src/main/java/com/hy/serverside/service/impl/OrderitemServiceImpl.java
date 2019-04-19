package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.mapper.OrderitemMapper;
import com.hy.serverside.service.IOrderitemService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderitemServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:18
 * @Version: 1.0
 */
@Service
public class OrderitemServiceImpl extends ServiceImpl<OrderitemMapper, Orderitem> implements IOrderitemService {
}
