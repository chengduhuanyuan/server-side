package com.hy.serverside.controller;

import com.hy.serverside.entity.Imglist;
import com.hy.serverside.service.ImglistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨席杰
 * @date 2019/4/26 11:45
 */
@RestController
public class ImglistController {
    @Autowired
    private ImglistService imglistService;

    @GetMapping("/getImglist")
    public Map<String,Object> getImglist(){
        List<Imglist> list = imglistService.list();
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("code","0");
        return map;
    }

}
