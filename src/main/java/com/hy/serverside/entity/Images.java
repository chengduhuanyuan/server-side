package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Images
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:17
 * @Version: 1.0
 */
@Data
public class Images implements Serializable {
    private static final long serialVersionUID = -8481669078848043766L;
    private String productId;
    private String imgUrl;
}
