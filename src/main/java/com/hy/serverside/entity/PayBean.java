package com.hy.serverside.entity;

import lombok.Data;

/**
 * @ClassName: PayBean
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/25 15:03
 * @Version: 1.0
 */
@Data
public class PayBean {
    private String openid;
    private String outTradeNo;
    private String body;
    private String totalFee;
}
