package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Orderitem
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:46
 * @Version: 1.0
 */
@Data
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1036850135458722842L;
    private Integer id;

    private Integer pid;

    private Integer oid;

    private Integer uid;

    private Integer number;

}
