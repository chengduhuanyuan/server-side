package com.hy.serverside.controller;


import com.hy.serverside.entity.Order;
import com.hy.serverside.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     *  生成订单
     * @param order
     * @return
     */
    @GetMapping("/saveByCart")
    public boolean createOrder(@RequestParam Order order){
        return orderService.save(order);
    }




}
