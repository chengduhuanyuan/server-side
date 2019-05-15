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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public JsonData Sellto(String parentName,String subclassName){
        User byopenId = userService.getByopenId(parentName);
        if(byopenId.getLevel()==0){
            return new JsonData(null,"升级为会员才能分销",false);
        }else {
            boolean b = userService.saveSellto(parentName, subclassName);
            return new JsonData(null,"",b);
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

    /***
    *@Description 登录
    *@Param [response]
    *@Return void
    *@Author 杨席杰
    *@Date 2019/5/15
    *@Time 9:16
    */
    @GetMapping("/login")
    public JsonData login(String openId,String Name){
        boolean b=false;
        User user = userService.getByopenId(openId);
        if (user==null){
            User u=new User(Name,openId,0);
             b = userService.save(u);
             userService.svaeNode(Name);
        }
        return new JsonData(null,"",b);
    }
    
    /***
    *@Description 查询是不是会员
    *@Param [response]
    *@Return void
    *@Author 杨席杰
    *@Date 2019/5/15
    *@Time 10:58
    */
    @GetMapping("/getMember")
    public JsonData member(String openId){
        User byopenId = userService.getByopenId(openId);
        if(byopenId.getLevel()==0){
            return new JsonData(0,"不是会员",false);
        }else{
            return new JsonData(byopenId.getLevel(),"是会员",true);
        }

    }


    @GetMapping("forword")
    public void forword(HttpServletResponse response){
//        ModelAndView mv = new ModelAndView("redirect:http://www.baidu.com");
        try {
            response.sendRedirect("http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
