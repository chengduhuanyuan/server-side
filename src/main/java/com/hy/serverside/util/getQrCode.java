package com.hy.serverside.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * @author 杨席杰
 * @date 2019/5/9 18:02
 */

public class getQrCode {
    public static String getQrCode(String setCode) throws Exception {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        String accessToken =new RedisCacheUtil().getRedisCache(Constant.ACCESS_TOKEN_KEY, redisTemplate);
        System.out.println(accessToken);
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
        System.out.println(url);
        String res = post(url, setCode);
        return res;
    }
    public static String post(String url,String data){
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            //设置为可输出数据状态
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //获取输出流
            OutputStream os = connection.getOutputStream();
            //写出数据
            os.write(data.getBytes(Charset.forName("utf-8")));
            os.close();
            //获取输入流，读取数据
            InputStream is =connection.getInputStream();
            StringBuilder sb =new StringBuilder();
            byte[] b =new byte[1024];
            int len;
            while((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
