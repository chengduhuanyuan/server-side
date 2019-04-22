package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.config.RedisCacheMybatis;
import com.hy.serverside.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:42
 * @Version: 1.0
 */
@CacheNamespace(implementation = RedisCacheMybatis.class)
public interface UserMapper extends BaseMapper<User> {
    @Options
    User getUserTest(String id);

    @Options
    boolean addUser(User user);
}
