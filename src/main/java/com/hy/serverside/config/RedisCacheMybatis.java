package com.hy.serverside.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: RedisCacheMybatis
 * @Description: TODO reids获取mybatis二级缓存
 * @Author: Kaiser
 * @Date: 2019/4/16 10:16
 * @Version: 1.0
 */
public class RedisCacheMybatis implements Cache {
    /**
     *  id:缓存的ID
     */
    private String id;
    /**
     *  lock:读写锁
     */
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public RedisCacheMybatis(String id){
        if (id == null){
            throw new IllegalArgumentException("cache instance require id");
        }
        this.id = id;
    }

    private void injectTemplate(){
        if (redisTemplate == null){
            this.redisTemplate =(RedisTemplate<String, Object>) SpringContextHolder.getApplicationContext().getBean("redisJsonTemplate");
        }
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        injectTemplate();
        redisTemplate.opsForValue().set(key.toString(),value);
    }

    @Override
    public Object getObject(Object key) {
        injectTemplate();
        return redisTemplate.opsForValue().get(key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        return redisTemplate.delete(key.toString());
    }

    @Override
    public void clear() {
        injectTemplate();
        Set<String> keys = redisTemplate.keys("*".concat(this.id).concat("*"));
        if (!CollectionUtils.isEmpty(keys)){
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getSize() {
        injectTemplate();
        return redisTemplate.execute((RedisCallback<Long>)(RedisServerCommands::dbSize)).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.lock;
    }
}
