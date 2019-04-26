package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Annieorder
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:45
 * @Version: 1.0
 */
@Data
public class Annieorder implements Serializable {

    private static final long serialVersionUID = -3448804448259661923L;
    private String id;

    private double goodsPrices;

    private String addressId;

    private String userMessage;

    private String createDate;

    private String payDate;

    private String deliveryDate;

    private String confirmDate;

    private String openid;

//    1待付款 2代发货 3待收货
    private int status;

    private List<Product> list;

}
