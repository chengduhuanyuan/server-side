package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Productimage
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:48
 * @Version: 1.0
 */
@Data
public class Productimage implements Serializable {
    private static final long serialVersionUID = 2454617282259807183L;
    private String id;

    private String pid;

    private String type;
}
