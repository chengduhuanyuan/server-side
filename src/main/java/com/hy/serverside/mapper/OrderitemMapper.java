package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.entity.ShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: OrderitemMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:01
 * @Version: 1.0
 */
public interface OrderitemMapper extends BaseMapper<Orderitem> {
    /**
     *  查询购物车信息
     * @param openid 用户
     * @return
     */
    List<ShopCart> getShopCartList(@Param("openid") String openid);

    /**
     *  批量删除购物车数据
     * @param orderitemIds
     * @return
     */
    int delShopCartPro(List<String> orderitemIds);

    /**
     *  立即购买获取
     * @param openid
     * @param productId
     * @return
     */
    List<ShopCart> getOneShopCart(@Param("openid") String openid,@Param("productId") String productId);
}
