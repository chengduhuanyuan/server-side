package com.hy.serverside.controller;

import com.hy.serverside.entity.Images;
import com.hy.serverside.entity.User;
import com.hy.serverside.service.IImagesService;
import com.hy.serverside.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private IImagesService imagesService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/test")
    public User test(@RequestParam String id){
        return userService.getTest(id);
    }

    /***
    *@Description 拉取用户信息时，将微信id存入数据库
    *@Param 
    *@Return 
    *@Author 杨席杰
    *@Date 2019/4/21
    *@Time 15:24
    */
    @GetMapping("/addUser")
    public void addUser(User user){
        boolean save = userService.save(user);
    }


}
