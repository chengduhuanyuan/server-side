package com.hy.serverside.controller;


import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.service.IOrderitemService;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.IdUtil;
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
@RequestMapping("/orderitem")
public class OrderitemController {
    @Autowired
    private IOrderitemService orderitemService;
    /**
     *  立即购买
     * @param openId
     * @param goodsId
     * @param num
     * @return
     */
    @GetMapping("/purchaseNow")
    public JsonData addCart(@RequestParam String openId, @RequestParam String goodsId, @RequestParam Integer num){
        if (openId != null){
            String orderId = IdUtil.getInstance().generateOrderNo();
            Orderitem orderitem = new Orderitem(IdUtil.getInstance().generateIdNo(), goodsId, orderId, openId, num);
            boolean save = orderitemService.save(orderitem);
            if (save) {
                return new JsonData(null,"添加成功",true);
            }
            return new JsonData(null,"购买失败",false);
        }
        return new JsonData(null,"没有登录",false);
    }
}
