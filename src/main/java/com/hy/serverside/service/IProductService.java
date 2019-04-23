package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Product;

/**
 * @ClassName: IProductService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:12
 * @Version: 1.0
 */
public interface IProductService extends IService<Product> {


    int Favorite(String openid,String goodid);

    int addFavorite(String openId, String goodsId);
}
