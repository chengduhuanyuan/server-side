package com.hy.serverside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Address
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/25 10:12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = -3787215498528627L;
    private String id;
    private String openid;
    private String mobile;
    private String receiverName;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String addressDetail;
    private boolean isDefault;
}
