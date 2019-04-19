package com.hy.serverside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName: HttpResult
 * @Description: TODO http响应结果封装
 * @Author: Kaiser
 * @Date: 2019/4/17 16:02
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult {
    /**
     *  响应码
     */
    private int code;
    /**
     *  响应体
     */
    private String body;
}
