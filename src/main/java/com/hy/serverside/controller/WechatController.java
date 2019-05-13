package com.hy.serverside.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.serverside.entity.User;
import com.hy.serverside.service.IHttpService;
import com.hy.serverside.service.IUserService;
import com.hy.serverside.util.Constant;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.RedisCacheUtil;
import com.hy.serverside.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WechatController
 * @Description: TODO 微信后台交互
 * @Author: Kaiser
 * @Date: 2019/4/17 11:04
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
public class WechatController {
    @Autowired
    private IHttpService httpService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IUserService userService;

    private String getWechatAccessToken(String accessToken,String openid,String scope) throws Exception {
        if (Constant.WECHAT_CHECK_SCOPE.equals(scope)){
            Map<String,Object> map = new HashMap<>(3);
            map.put(Constant.WECHAT_ACCESS_TOKEN_KEY,accessToken);
            map.put(Constant.WECHAT_OPENID_KEY,openid);
            map.put(Constant.LANG,Constant.LANG_VALUE);
            return httpService.doGet(Constant.WECHAT_GETUSERINFO_URL,map);
        }
        return "scope is wrong";
    }

    /**
     *  公众号拉取授权
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/wechat/authorization")
    public void getCode() throws Exception {
        Map<String,Object> map = new HashMap<>(5);
        map.put(Constant.APPID_KEY,Constant.WECHAT_APPID_VALUE);
        map.put(Constant.WECHAT_REDIRECT_URI_KEY, URLEncoder.encode(Constant.WECHAT_REDIRECT_URI_VALUE,"UTF-8"));
        map.put(Constant.WECHAT_RESPONSE_TYPE_KEY,Constant.WECHAT_RESPONSE_TYPE_VALUE);
        map.put(Constant.WECHAT_SCOPE_KEY,Constant.WECHAT_SCOPE_VALUE);
        map.put(Constant.WECHAT_STATE_KEY,Constant.WECHAT_STATE_VALUE);
        httpService.doGet(Constant.WECHAT_GET_CODE_URL, map);
    }
    @CrossOrigin
    @GetMapping("/wechat/getUserInfo")
    public JsonData getUserInfo(String code) throws Exception {
        Map<String,Object> map = new HashMap<>(4);
        Map<String,Object> check = new HashMap<>(2);
        Map<String,Object> refreshMap = new HashMap<>(3);
        map.put(Constant.APPID_KEY,Constant.WECHAT_APPID_VALUE);
        map.put(Constant.APPSECRET_KEY,Constant.WECHAT_SECRET_VALUE);
        map.put(Constant.WECHAT_CODE_KEY,code);
        map.put(Constant.GRANT_TYPE_KEY,Constant.WECHAT_GRANT_TYPE_VALUE);
        JSONObject object = JSONObject.parseObject(httpService.doGet(Constant.WECHAT_CODE2TOKEN_URL, map));
        String openid = object.getString("openid");
        String accessToken = object.getString("access_token");
        String refreshToken = object.getString("refresh_token");
        String scope = object.getString("scope");
        check.put(Constant.WECHAT_ACCESS_TOKEN_KEY,accessToken);
        check.put(Constant.WECHAT_OPENID_KEY,openid);
        JSONObject msg = JSONObject.parseObject(httpService.doGet(Constant.WECHAT_CHECKTOKEN_URL, map));
        if (Constant.CHECK_CODE.equals(msg.get(Constant.WECHAT_ERRCODE))) {
            String userInfo = getWechatAccessToken(accessToken, openid, scope);
            return new JsonData(userInfo,"success",true);
        } else {
            refreshMap.put(Constant.APPID_KEY,Constant.WECHAT_APPID_VALUE);
            refreshMap.put(Constant.GRANT_TYPE_KEY,Constant.WECHAT_GRANT_TYPE_REFRESH_VALUE);
            refreshMap.put(Constant.WECHAT_REFRESH_TOKEN_KEY,refreshToken);
            JSONObject refresh = JSONObject.parseObject(httpService.doGet(Constant.WECHAT_REFRESH_TOKEN_URL, refreshMap));
            String refreshScope = refresh.getString("scope");
            String refreshAccessToken = refresh.getString("access_token");
            String refreshOpenid = refresh.getString("openid");
            String wechatAccessToken = getWechatAccessToken(refreshAccessToken, refreshOpenid, refreshScope);
            return new JsonData(wechatAccessToken,"success",true);
        }
    }

    @GetMapping("/wechat/jscode2session")
    public String getSession(@RequestParam String jsCode,@RequestParam String nickName) throws Exception {
        Map<String,Object> map = new HashMap<>(5);
        map.put(Constant.APPID_KEY, Constant.APPID);
        map.put(Constant.APPSECRET_KEY,Constant.APPSECRET);
        map.put(Constant.JS_CODE_KEY,jsCode);
        map.put(Constant.GRANT_TYPE_KEY,Constant.CODE2SESSION_GRANT_TYPE);
        map.put(Constant.NAME_KEY,nickName);
        return httpService.doGet(Constant.CODE2SESSION_URL, map);
    }

    /**
     *  每2小时获取一次token
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    private void timerGetAccessToken() throws Exception {
        Map<String,Object> map = new HashMap<>(3);
        map.put(Constant.GRANT_TYPE_KEY,Constant.ACCESS_TOKEN_GRANT_TYPE);
        map.put(Constant.APPID_KEY,Constant.APPID);
        map.put(Constant.APPSECRET_KEY,Constant.APPSECRET);
        String s = httpService.doGet(Constant.ACCESS_TOKEN_URL, map);
        JSONObject object = JSONObject.parseObject(s);
        RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
        redisCacheUtil.setRedisCache(Constant.ACCESS_TOKEN_KEY,object.getString("access_token"),
                Long.parseLong(object.getString("expires_in")),redisTemplate);
    }
    @GetMapping("/wechat/getAccessToken")
    public String getAccessToken(){
        String accessToken = null;
        try {
            accessToken = new RedisCacheUtil().getRedisCache(Constant.ACCESS_TOKEN_KEY, redisTemplate);
        } catch (NullPointerException e){
            try {
                this.timerGetAccessToken();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (accessToken == null){
            return new RedisCacheUtil().getRedisCache(Constant.ACCESS_TOKEN_KEY, redisTemplate);
        }
        return accessToken;
    }

    @GetMapping("/unifiedOrder")
    public Map<String,String> unifiedOrder(){
        WXPayUtil wxPayUtil = new WXPayUtil();
        return null;
    }

    /**
     *  将用户信息保存到数据库
     * @param nickName 名称
     * @param openid openid
     * @return
     */
    @GetMapping("/wechat/saveUser")
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


}
