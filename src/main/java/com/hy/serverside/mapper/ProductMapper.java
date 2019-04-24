package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<Product> getCateAll(@Param("page") int page, @Param("size") int size,@Param("category") int category);

    int getCateSize(@Param("category") int category);
}
