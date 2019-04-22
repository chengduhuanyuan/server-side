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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/wechat/jscode2session")
    public String getSession(@RequestParam String jsCode,@RequestParam String nickName) throws Exception {
        System.out.println("jscode:"+jsCode+"           "+"nickName:"+nickName);
        Map<String,Object> map = new HashMap<>(5);
        map.put(Constant.APPID_KEY, Constant.APPID);
        map.put(Constant.APPSECRET_KEY,Constant.APPSECRET);
        map.put(Constant.JS_CODE_KEY,jsCode);
        map.put(Constant.GRANT_TYPE_KEY,Constant.CODE2SESSION_GRANT_TYPE);
        map.put(Constant.NAME_KEY,nickName);
        String get = httpService.doGet(Constant.CODE2SESSION_URL, map);
        System.out.println("请求数据："+get);
        return get;
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
        System.out.println("得到信息："+s);
        JSONObject object = JSONObject.parseObject(s);
        RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
        redisCacheUtil.setRedisCache(Constant.ACCESS_TOKEN_KEY,object.getString("access_token"),
                Long.parseLong(object.getString("expires_in")),redisTemplate);
        System.out.println("...........");
    }
    @GetMapping("/wechat/getAccessToken")
    public String getAccessToken(){
        try {
            this.timerGetAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedisCacheUtil().getRedisCache(Constant.ACCESS_TOKEN_KEY, redisTemplate);
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
            User user = new User(nickName,openid);
            boolean b = userService.save(user);
            if (b){
                return new JsonData(null,"保存成功",true);
            }
        }
        return new JsonData(null,"保存失败",false);
    }


}
