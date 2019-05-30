package com.hy.serverside.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.hy.serverside.util.Constant;
import com.hy.serverside.util.TimeUtil;
import com.hy.serverside.wechat.HttpRequest;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.RedisCacheUtil;
import com.hy.serverside.wechat.WeChatUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WechatController
 * @Description: TODO 微信后台交互
 * @Author: Kaiser
 * @Date: 2019/4/17 11:04
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class WechatController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private WxMpService wxMpService;

    private HttpRequest httpRequest = new HttpRequest();
    private RedisCacheUtil redisCacheUtil = new RedisCacheUtil();

    @Scheduled(cron = "0 0/59 0/1 * * ? ")
    public void saveAccessToken() {
        System.out.println("开始执行......");
        String accessToken = httpRequest.getAccessToken();
        String apiTiket = httpRequest.getJsApiTiket(accessToken);
        redisCacheUtil.setMyRedisCache(Constant.ACCESS_TOKEN_KEY,accessToken,7200,redisTemplate);
        redisCacheUtil.setMyRedisCache(Constant.JSAPI_TICKET_KEY,apiTiket,7200,redisTemplate);
    }

    @GetMapping("/getJsApiSign")
    public JsonData getJSApiSign(@RequestParam String url){
        long timeStmap = TimeUtil.getSignTimeStmap();
        String nonceStr = WXPayUtil.generateNonceStr();
        String ticket = redisCacheUtil.getMyRedisCache(Constant.JSAPI_TICKET_KEY, redisTemplate);
        if (ticket != null) {
            String signature = WeChatUtil.jsApiSignature(ticket, nonceStr, timeStmap, url);
            Map<String,String> map = new HashMap<>(3);
            map.put("time_stamp",String.valueOf(timeStmap));
            map.put("nonce_str",nonceStr);
            map.put("signature",signature);
            return new JsonData(map,"success",true);
        }
        return new JsonData(null,"redis fail",false);
    }

    @GetMapping("/userInfo")
    public JsonData userInfo(@RequestParam("code") String code) throws WxErrorException {
        WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(code);
        WxMpUser user = wxMpService.oauth2getUserInfo(token, null);
        return new JsonData(user,"success",true);
    }

    /**
     *  将用户信息保存到数据库
     * @param nickName 名称
     * @param openid openid
     * @return
     */
    /*@GetMapping("/wechat/saveUser")
    public JsonData saveUser(@RequestParam String nickName,@RequestParam String openid){
        User one = userService.getOne(new QueryWrapper<User>().eq("openid", openid));
        if (one == null){
            User user = new User(nickName,openid,0);
            boolean b = userService.save(user);
            if (b){
                return new JsonData(null,"保存成功",true);
            }
        }
        return new JsonData(null,"保存失败",false);
    }
    */

}
