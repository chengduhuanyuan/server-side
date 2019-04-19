package com.hy.serverside.service.impl;

import com.hy.serverside.entity.HttpResult;
import com.hy.serverside.service.IHttpService;
import com.hy.serverside.util.Constant;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HttpServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/17 16:28
 * @Version: 1.0
 */
@Service
public class HttpServiceImpl implements IHttpService {

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig config;

    @Override
    public String doGet(String url) throws Exception {
        //声明请求
        HttpGet httpGet = new HttpGet(url);
        //装载配置信息
        httpGet.setConfig(config);
        //发起请求
        CloseableHttpResponse execute = this.httpClient.execute(httpGet);
        //判断状态码
        if(execute.getStatusLine().getStatusCode() == Constant.STATUS_CODE_SUCCESS){
            return EntityUtils.toString(execute.getEntity(), Constant.HTTP_CHARACTER);
        }
        return null;
    }

    @Override
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            //遍历map，拼接请求参数
            for (Map.Entry<String,Object> entry: map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(),entry.getValue().toString());
            }
        }
        return this.doGet(uriBuilder.build().toString());
    }

    @Override
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url,null);
    }

    @Override
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        //判断map是否为空，不为空则遍历，封装from表单对象
        if (map != null){
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String,Object> entry: map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
            //构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,Constant.HTTP_CHARACTER);
            //将表单放入post中
            httpPost.setEntity(urlEncodedFormEntity);
        }
        //发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity(),Constant.HTTP_CHARACTER));
    }
}
