package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.config.RedisCacheMybatis;
import com.hy.serverside.entity.Address;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

/**
 * @ClassName: AddressMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/25 10:15
 * @Version: 1.0
 */
@CacheNamespace(implementation = RedisCacheMybatis.class)
public interface AddressMapper extends BaseMapper<Address> {
    /**
     *  执行修改默认地址的存储过程
     * @param id
     * @param openid
     */
    @Select("call default_address(#{a_id},#{a_openid})")
    @Options(statementType = StatementType.CALLABLE)
    void setDefaultAddress(@Param("a_id") String id, @Param("a_openid") String openid);
}
