package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Product;
import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:20
 * @Version: 1.0
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
}
