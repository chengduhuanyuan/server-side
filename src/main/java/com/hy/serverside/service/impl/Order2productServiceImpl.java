package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Order2product;
import com.hy.serverside.mapper.Order2productMapper;
import com.hy.serverside.service.IOrder2productService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: Order2productServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/26 10:24
 * @Version: 1.0
 */
@Service
public class Order2productServiceImpl extends ServiceImpl<Order2productMapper,Order2product> implements IOrder2productService {
}
