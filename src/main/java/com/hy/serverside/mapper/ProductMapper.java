package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.config.RedisCacheMybatis;
import com.hy.serverside.entity.Product;
import com.hy.serverside.entity.ProductDetail;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ProductMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:01
 * @Version: 1.0
 */
@CacheNamespace(implementation = RedisCacheMybatis.class)
public interface ProductMapper extends BaseMapper<Product> {
    String Favorite(String openId,String goodsId);

    boolean addFavorite(String openId, String goodsId);

    List<Product> getCateAll(@Param("page") int page, @Param("size") int size,@Param("category") int category);

    int getCateSize(@Param("category") int category);

    List<Product> searchkeyword(int page, int size, String searchKeyWords,int category);

    List<Product> getProductById(int pid);

    ProductDetail getProductDetailById (String id);
}
