package com.hy.serverside.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.serverside.entity.Annieorder;
import com.hy.serverside.entity.Order2product;
import com.hy.serverside.service.IAnnieorderService;
import com.hy.serverside.service.IOrder2productService;
import com.hy.serverside.service.IOrderitemService;
import com.hy.serverside.util.IdUtil;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class AnnieorderController {
    @Autowired
    private IAnnieorderService orderService;
    @Autowired
    private IOrder2productService order2productService;
    @Autowired
    private IOrderitemService orderitemService;

    @GetMapping("/creatOrder")
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonData createOrder(String openid, String totalPrice, String payList){
        JSONArray jsonArray = JSON.parseArray(payList);
        int size = jsonArray.size();
        if (size>0){
            String orderNo = IdUtil.getInstance().generateOrderNo();
            List<Order2product> list = new ArrayList<>(size);
            Map<String,String> orderPro = new HashMap<>(size);
            List<String> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                item.add(object.get("orderitemId").toString());
                JSONObject product = JSONObject.parseObject(object.get("product").toString());
                orderPro.put(product.get("id").toString(),object.get("num").toString());
            }
            orderPro.forEach((key,value) -> {
                Order2product order2product = new Order2product(orderNo,key,Integer.valueOf(value));
                list.add(order2product);
                System.out.println(key+" : "+ value);
            });
            Annieorder annieorder = new Annieorder();
            annieorder.setId(orderNo);
            annieorder.setOpenid(openid);
            annieorder.setCreateDate(TimeUtil.getCurrentTime());
            annieorder.setGoodsPrices(Double.valueOf(totalPrice));
            annieorder.setStatus(1);
            System.out.println(annieorder.toString());
            boolean save = orderService.save(annieorder);
            boolean saveBatch = order2productService.saveBatch(list);
            boolean removeByIds = orderitemService.delShopCart(item);
            System.out.println("是否执行删除操作："+removeByIds);
            if (save&&saveBatch&&removeByIds){
                return new JsonData(null,"success",true);
            }
        }
        return new JsonData(null,"创建订单失败",false);
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
        List<Annieorder> list = orderService.getOrderAll(openId, Integer.parseInt(orderStatus), Integer.parseInt(page), Integer.parseInt(size));
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
        Map<String, Object> map=new HashMap<>();
        map.put("code",i-1);
        map.put("errerTips","");
        return map;
    }


}
