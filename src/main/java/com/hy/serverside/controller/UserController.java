package com.hy.serverside.controller;

import com.hy.serverside.entity.Images;
import com.hy.serverside.entity.User;
import com.hy.serverside.service.IImagesService;
import com.hy.serverside.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/test")
    public User test(@RequestParam Integer id){
        return userService.getTest(id);
    }

    @GetMapping("/test2")
    public List<Images> test2(@RequestParam Integer id){
        return imagesService.getAll(id);
    }
}
