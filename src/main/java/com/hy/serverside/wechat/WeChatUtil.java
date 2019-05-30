package com.hy.serverside.wechat;

/**
 * @ClassName: WeChatUtil
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/29 17:09
 * @Version: 1.0
 */
public class WeChatUtil {
    public static String jsApiSignature(String jsapiTicket,String noncestr,long timestamp,String url){
        String urlVal = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        return SHA1.encode(String.format(urlVal,jsapiTicket,noncestr,String.valueOf(timestamp),url));
    }
}
