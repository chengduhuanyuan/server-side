package com.hy.serverside.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.serverside.entity.User;
import com.hy.serverside.service.IUserService;
import com.hy.serverside.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
}
