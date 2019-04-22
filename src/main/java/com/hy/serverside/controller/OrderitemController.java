package com.hy.serverside.controller;


import com.hy.serverside.entity.Orderitem;
import com.hy.serverside.service.IOrderitemService;
import com.hy.serverside.util.Constant;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.IdUtil;
import com.hy.serverside.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *  立即购买
     * @param openId
     * @param goodsId
     * @param accessToken
     * @param num
     * @return
     */
    @GetMapping("/purchaseNow")
    public JsonData addCart(@RequestParam String openId, @RequestParam String goodsId, String accessToken, @RequestParam Integer num){
        String redis_token = new RedisCacheUtil().getRedisCache(Constant.ACCESS_TOKEN_KEY, redisTemplate);
        System.out.println("传过来的access_token:"+accessToken);
        System.out.println("redis中的access_token:"+redis_token);
        if (accessToken.equals(redis_token)){
            String orderId = IdUtil.getOrderId();
            Orderitem orderitem = new Orderitem(IdUtil.getId(), goodsId, orderId, openId, num);
            boolean save = orderitemService.save(orderitem);
            if (save) {
                return new JsonData(null,"添加成功",true);
            }
            return new JsonData(null,"购买失败",false);
        }
        return new JsonData(null,"token错误",false);
    }
}
