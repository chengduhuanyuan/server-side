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
     * @param redisTemplate redis操作管理
     */
    public void setRedisCache(String key,Object value,long time,RedisTemplate redisTemplate){
        if (redisTemplate.hasKey(key)){
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
        }else {
            redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
        }
    }

    /**
     *  取出缓存
     * @param key 键
     * @param redisTemplate 管理器
     * @return value
     */
    public String getRedisCache(String key,RedisTemplate redisTemplate){
        Object o = redisTemplate.opsForValue().get(key);
        return o.toString();
    }
}
