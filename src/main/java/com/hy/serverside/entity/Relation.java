package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨席杰
 * @date 2019/4/28 16:46
 */
@Data
public class Relation implements Serializable {
    private int id;
    private String oneself;
    private String  superior;
    private String opentime;
    private int payfor;
}
