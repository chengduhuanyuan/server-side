package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    private String name;

    private String subTitle;

    private Float originalPrice;

    private Float promotePrice;

    private Integer stock;

    private String cid;

    private LocalDateTime createDate;

    private String cover;

    private String detailInfo;

    private List<Images> images;
}
