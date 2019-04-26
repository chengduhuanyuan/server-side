package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨席杰
 * @date 2019/4/26 14:05
 */
@Data
public class Discover implements Serializable {
    private String pId;
    private String logo;
    private String intro;
}
