package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductDetail
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/11 9:36
 * @Version: 1.0
 */
@Data
public class ProductDetail implements Serializable {
    private static final long serialVersionUID = -513363866795735080L;
    private String id;

    private String productName;

    private Float originalPrice;

    private Float promotePrice;

    private Float privilegePrice;

    private Integer stock;

    private String sku;

    private String cover;

    private String detailInfo;

    private List<Images> images;
}
