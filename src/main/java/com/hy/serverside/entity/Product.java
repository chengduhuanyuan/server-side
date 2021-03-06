package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Product
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:47
 * @Version: 1.0
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -7470366765073757516L;
    private String id;

    private String productName;

    private Float originalPrice;

    private Float promotePrice;

    private Float privilegePrice;

    private Integer stock;

    private String sku;

    private String cover;

    private String detailInfo;
}
