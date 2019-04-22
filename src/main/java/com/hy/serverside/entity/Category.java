package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Category
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:43
 * @Version: 1.0
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 9084824875447820209L;
    private String id;
    private String name;
}
