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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView Sellto(String parentName,String subclassName){
        boolean b = userService.saveSellto(parentName, subclassName);
        if(b){
            ModelAndView mv = new ModelAndView("redirect:../www.baidu.com");
            return mv;
        }else {
//          失败返回的地址
            ModelAndView mv = new ModelAndView("redirect:../www.baidu.com");
            return mv;
        }

    }

    /***
    *@Description 查询分销商的上级
    *@Param
    *@Return
    *@Author 杨席杰
    *@Date 2019/5/14
    *@Time 9:53
    */
    @GetMapping("/getParentName")
    public JsonData getParentName(String subclassName){
        String parentName=userService.getParentName(subclassName);
        if(parentName==null){
            return new JsonData(null,"fail",false);
        }else {
            return new JsonData(parentName,"success",true);
        }
    }

    @GetMapping("forword")
    public ModelAndView forword(){
        ModelAndView mv = new ModelAndView("redirect:../www.baidu.com");
        return mv;
    }

}
