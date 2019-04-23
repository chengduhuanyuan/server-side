package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Product;

/**
 * @ClassName: ProductMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:01
 * @Version: 1.0
 */
public interface ProductMapper extends BaseMapper<Product> {
    String Favorite(String openId,String goodsId);

    boolean addFavorite(String openId, String goodsId);
}
