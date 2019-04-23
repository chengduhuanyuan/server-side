package com.hy.serverside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:38
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -2987954675395430545L;

    private String name;

    private String openid;

    private String mobile;
}
