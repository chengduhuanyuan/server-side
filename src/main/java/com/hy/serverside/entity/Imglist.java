package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨席杰
 * @date 2019/4/26 11:36
 */
@Data
public class Imglist implements Serializable {
    private static final long serialVersionUID = 6086025891870029724L;
    private String id;
    private String url;
    private String img;
    private String picUrl;
    private String title;
}
