package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Property
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:49
 * @Version: 1.0
 */
@Data
public class Property implements Serializable {
    private static final long serialVersionUID = 8620544716370295173L;
    private Integer id;

    private Integer cid;

    private String name;
}
