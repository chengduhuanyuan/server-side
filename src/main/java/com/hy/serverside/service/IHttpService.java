package com.hy.serverside.service;

import com.hy.serverside.entity.HttpResult;

import java.util.Map;

/**
 * @ClassName: IHttpService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/17 16:19
 * @Version: 1.0
 */
public interface IHttpService {
    /**
     * 不带参数的get请求，如果状态码为200返回body,否则返回null
     * @param url
     * @return
     * @throws Exception
     */
    String doGet(String url) throws Exception;

    /**
     * 带参数的get请求
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    String doGet(String url, Map<String, Object> map) throws Exception;

    /**
     *  不带参数的post请求
     * @param url
     * @return
     * @throws Exception
     */
    HttpResult doPost(String url) throws Exception;

    /**
     *  带参数的post请求
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    HttpResult doPost(String url, Map<String, Object> map) throws Exception;
}
