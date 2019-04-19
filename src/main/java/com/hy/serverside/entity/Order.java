package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private Integer id;

    private String orderCode;

    private String address;

    private String post;

    private String receiver;

    private String mobile;

    private String userMessage;

    private LocalDateTime createDate;

    private LocalDateTime payDate;

    private LocalDateTime deliveryDate;

    private LocalDateTime confirmDate;

    private Integer uid;

    private String status;

}
