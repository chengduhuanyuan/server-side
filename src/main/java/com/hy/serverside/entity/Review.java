package com.hy.serverside.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Review
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:50
 * @Version: 1.0
 */
@Data
public class Review implements Serializable {

    private static final long serialVersionUID = -4002469637735797095L;
    private String id;

    private String content;

    private String openid;

    private String pid;

    private String createDate;
}
