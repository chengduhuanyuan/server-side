package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Productimage;
import com.hy.serverside.mapper.ProductimageMapper;
import com.hy.serverside.service.IProductimageService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProductimageServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:19
 * @Version: 1.0
 */
@Service
public class ProductimageServiceImpl extends ServiceImpl<ProductimageMapper, Productimage> implements IProductimageService {
}
