package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.entity.ShopCart;

import java.util.List;

/**
 * @ClassName: IOrderitemService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:12
 * @Version: 1.0
 */
public interface IOrderitemService extends IService<Orderitem> {
    /**
     *  获取购物车信息
     * @param openid
     * @return
     */
    List<ShopCart> getShopCart(String openid);

    /**
     *  批量删除购物车
     * @param oritemIds
     * @return
     */
    boolean delShopCart(List<String> oritemIds);

    /**
     *  立即购买
     * @param openid
     * @param productId
     * @return
     */
    List<ShopCart> getOneShopCart(String openid,String productId);
}
