package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:38
 * @Version: 1.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2987954675395430545L;

    private Integer id;

    private String name;

    private String password;

    private String salt;
}
