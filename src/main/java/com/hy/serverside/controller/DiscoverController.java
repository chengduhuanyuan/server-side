package com.hy.serverside.controller;

import com.hy.serverside.entity.Discover;
import com.hy.serverside.service.IDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨席杰
 * @date 2019/4/26 14:08
 */

@RestController
@RequestMapping("/pic")
public class DiscoverController {
    @Autowired
    private IDiscoverService iDuscoverService;

    @GetMapping("discoverList")
    public Map<String,Object> discover(){
        List<Discover> list = iDuscoverService.list();
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("code",0);
        return map;
    }
}
