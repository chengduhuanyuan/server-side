package com.hy.serverside.controller;

import com.hy.serverside.util.JsonData;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private WxMpService wxMpService;

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
    
    /***
    *@Description 微信支付
    *@Param []
    *@Return com.hy.serverside.util.JsonData
    *@Author 杨席杰
    *@Date 2019/5/24
    *@Time 15:34
    */
    @PostMapping("/Payfor")
    public JsonData Payfor(){
        return new JsonData();
    }
}
