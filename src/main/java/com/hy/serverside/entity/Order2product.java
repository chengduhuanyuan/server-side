package com.hy.serverside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Order2product
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/26 10:20
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order2product implements Serializable {
    private static final long serialVersionUID = 2608438334412352293L;
    private String orderId;
    private String productId;
    private int productNum;
}
