package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Order;
import com.hy.serverside.mapper.OrderMapper;
import com.hy.serverside.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:17
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Override
    public int getOrderSize(String openid) {
        int size=baseMapper.getOrderSize(openid);
        return size;
    }

    @Override
    public List<Order> getOrderAll(String openid, int orderStatus, int page, int size) {
        page=(page-1)*size;
       List<Order> list= baseMapper.getOrderAll(openid,orderStatus,page,size);
        return list;
    }

    @Override
    public Map<String,Object> getOrderById(String openid) {
        List<Order> orderList=baseMapper.getOrderById(openid);
//        待付款
        int pendingPayCount=0;
//        待发货
        int backrdersCount=0;
//        待收货
        int shippedCount=0;
        for(Order o:orderList){
            System.out.println(o.getStatus());
            if( o.getStatus()==1){
               pendingPayCount++;
           }
           if(o.getStatus()==2){
               backrdersCount++;
           }
           if(o.getStatus()==3){
               shippedCount++;
           }
        }
        Map<String,Object> map= new HashMap<>();
        map.put("pendingPayCount",pendingPayCount);
        map.put("backrdersCount",backrdersCount);
        map.put("shippedCount",shippedCount);
        return map;
    }

    @Override
    public int delOrder(String orderNo) {
        int i=baseMapper.delOrder(orderNo);
        return i;
    }

    @Override
    public int orderConfirm(String orderNo) {
        int i=baseMapper.orderConfirm(orderNo);
        return i;
    }
}
