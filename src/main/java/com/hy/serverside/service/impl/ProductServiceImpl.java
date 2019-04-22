package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Product;
import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public int Favorite(String openid,String goodid) {
        String favorite = baseMapper.Favorite(openid,goodid);
        System.out.print("");
        System.out.print("");
        System.out.print("");
        System.out.println(favorite);
        if (favorite==null){
            return 0;
        }
            return 1;

    }
}
