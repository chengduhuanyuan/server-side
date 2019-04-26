package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨席杰
 * @date 2019/4/26 11:36
 */
@Data
public class Imglist implements Serializable {
    private String id;
    private String advertUrl;
    private String picUrl;
    private String title;
}
