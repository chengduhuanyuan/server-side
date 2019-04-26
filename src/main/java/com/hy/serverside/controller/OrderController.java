package com.hy.serverside.controller;


import com.hy.serverside.entity.Order;
import com.hy.serverside.service.IOrderService;
import com.hy.serverside.service.IProductService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kaiser
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService iProductService;

    public Map<String,Object> getImglist(){

        Map<String,Object> map=new HashMap<>();
        return map;
    }


    @GetMapping("/creatOrder")
    public boolean createOrder(String openid, String totalPrice, Map<String,String> mapObj){
        System.out.println("openid:"+openid+"         totalPrice:"+totalPrice);
        System.out.println(mapObj.toString());
        return false;
    }

    /***
    *@Description 订单数量查询
    *@Param
    *@Return
    *@Author 杨席杰
    *@Date 2019/4/25
    *@Time 9:39
    */
    @GetMapping("/getOrderSize")
    public  Map<String,Object> getOrderSize(String openId){
        Map<String,Object> map=orderService.getOrderById(openId);
        map.put("code",0);
        return map;
    }
    /***
    *@Description 条件查询订单
    *@Param 
    *@Return 
    *@Author 杨席杰
    *@Date 2019/4/25
    *@Time 10:34
    */
    @GetMapping("/getOrderAll")
    @ResponseBody
    public Map<String,Object> getOrderAll(String openId, String orderStatus, String page, String size) {
        if (orderStatus == "") {
            orderStatus = "0";
        }
        List<Order> list = orderService.getOrderAll(openId, Integer.parseInt(orderStatus), Integer.parseInt(page), Integer.parseInt(size));
        int length = list.size();
        int total;
        if (length % 10 > 0) {
            total = length / 10 + 1;
        } else {
            total = length / 10;
        }

            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("list", list);
            map.put("pageNum", 1);
            map.put("pageSize", 10);
            map.put("page_total", total);
            map.put("reason", null);
            map.put("result", "005");
            map.put("totalCount", 0);
            return map;
        }
        
        /***
        *@Description 删除订单
        *@Param 
        *@Return 
        *@Author 杨席杰
        *@Date 2019/4/26
        *@Time 9:37
        */
        @GetMapping("/delOrder")
        public Map<String,Object> delOrder(String orderNo,String flag){
            int i=0;
            if(flag.equals("4")){
              i=orderService.delOrder(orderNo);
            }else if(flag.equals("3")){
                i=orderService.orderConfirm(orderNo);
            }
            System.out.println(flag);
            Map<String, Object> map = new HashMap<>();
            map.put("code",i-1);
            map.put("errerTips","");
            return map;
        }


}
