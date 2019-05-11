package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Product;
import com.hy.serverside.entity.ProductDetail;

import java.util.List;

/**
 * @ClassName: IProductService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:12
 * @Version: 1.0
 */
public interface IProductService extends IService<Product> {
    int Favorite(String openid, String goodid);

    int addFavorite(String openId, String goodsId);

    List<Product> getCateAll(int page, int size, String category);

    int getCateSize(String category);

    List<Product> searchkeyword(String page, String size, String searchKeyWords,String category);

    ProductDetail getProductDetailById(String id);
}
