package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Propertyvalue
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:49
 * @Version: 1.0
 */
@Data
public class Propertyvalue implements Serializable {
    private static final long serialVersionUID = -8616455092207653785L;
    private Integer id;

    private Integer pid;

    private Integer ptid;

    private String value;
}
