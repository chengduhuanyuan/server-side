package com.hy.serverside.util;

import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisCacheUtil
 * @Description: TODO 缓存工具类
 * @Author: Kaiser
 * @Date: 2019/4/18 11:26
 * @Version: 1.0
 */
public class RedisCacheUtil {

    /**
     *  设置redis缓存
     * @param key 键
     * @param value 值
     * @param time 过期时间
     */
    public void setMyRedisCache(String key,Object value,long time,RedisTemplate<String,Object> redisTemplate){
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     *  取出缓存
     * @param key 键
     * @return value
     */
    public String getMyRedisCache(String key,RedisTemplate<String,Object> redisTemplate){
        Object o = redisTemplate.opsForValue().get(key);
        String val = o.toString();
        if (val != null) {
            return val;
        } else {
            throw new NullPointerException("获取到的缓存是空");
        }
    }
}
