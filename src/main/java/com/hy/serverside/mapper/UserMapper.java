package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.config.RedisCacheMybatis;
import com.hy.serverside.entity.Relation;
import com.hy.serverside.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

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

    @Select("call addFound(#{parentName},#{subclassName})")
    @Options(statementType = StatementType.CALLABLE)
    void saveSellto(@Param("parentName") String parentName, @Param("subclassName") String subclassName);


    Relation getSuperior(String superior, String oneself);
//    查询分销上级
    String getParentName(String subclassName);

    User getByopenId(String openId);
}
