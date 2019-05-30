package com.hy.serverside.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hy.serverside.util.Constant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @ClassName: HttpRequest
 * @Description: TODO 实现请求
 * @Author: Kaiser
 * @Date: 2019/5/29 14:54
 * @Version: 1.0
 */
public class HttpRequest {

    private JSONObject urlRequest(String url){
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        JSONObject obj = null;
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == Constant.SUCCESS_CODE) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                obj = JSONObject.parseObject(resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public String getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        JSONObject object = this.urlRequest(String.format(url, Constant.APPID, Constant.WECHAT_SECRET_VALUE));
        return object.getString("access_token");
    }

    public String getJsApiTiket(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
        JSONObject object = this.urlRequest(String.format(url, accessToken));
        return object.getString("ticket");
    }
}
