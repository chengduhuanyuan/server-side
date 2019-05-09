package com.hy.serverside.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.serverside.entity.Relation;
import com.hy.serverside.entity.User;
import com.hy.serverside.service.IUserService;
import com.hy.serverside.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    public User test(@RequestParam String openid){
        return userService.getTest(openid);
    }

    @GetMapping("/getUserInfo")
    public JsonData getUserInfo(String openId){
        User user = userService.getOne(new QueryWrapper<User>().eq("openid", openId));
        if (user != null){
            return new JsonData(user,"success",true);
        }
        return new JsonData(null,"fail",false);
    }

    /***
    *@Description 分销扫码 接口
    *@Param [superior, oneself]
    *@Return com.hy.serverside.util.JsonData
    *@Author 杨席杰
    *@Date 2019/4/28
    *@Time 17:36
    */
    @GetMapping("/Sellto")
    public JsonData Sellto(String superior,String oneself){
        Relation relation=userService.getSuperior(superior,oneself);
        if(relation!=null){
            return new JsonData(null,"fail",false);
        }else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String opentime=df.format(new Date()).toString();
            boolean b=userService.saveSellto(superior,oneself,opentime);
            if(b){
                return new JsonData(relation,"success",b);
            }else {
                return new JsonData(null,"fail",false);
            }

        }
    }

}
