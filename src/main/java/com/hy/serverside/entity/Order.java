package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:45
 * @Version: 1.0
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -3448804448259661923L;
    private String id;

    private double goodsPrices;

    private int addressId;

    private String userMessage;

    private LocalDateTime createDate;

    private LocalDateTime payDate;

    private LocalDateTime deliveryDate;

    private LocalDateTime confirmDate;

    private String openid;

//    1待付款 2代发货 3待收货
    private int status;

    private List<Product> list;

}
