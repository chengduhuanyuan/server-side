package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ShopCart
 * @Description: TODO 购物车实体类
 * @Author: Kaiser
 * @Date: 2019/4/24 10:49
 * @Version: 1.0
 */
@Data
public class ShopCart implements Serializable {
    private static final long serialVersionUID = -1185403385119054601L;

    private String id;
    private Product product;
    private String orderId;
    private String openid;
    private int num;
    private boolean ischecked = false;
}
