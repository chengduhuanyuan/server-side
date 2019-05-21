package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨席杰
 * @date 2019/5/19 15:42
 */

@Data
public class Coupon implements Serializable {
    private String id;
    private String describe;
    private String offset;
    private String couponUser;
}
