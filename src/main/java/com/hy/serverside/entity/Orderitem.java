package com.hy.serverside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Orderitem
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:46
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1036850135458722842L;
    private String id;

    private String pid;

    private String oid;

    private String openid;

    private Integer number;

}
