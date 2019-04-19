package com.hy.serverside.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfig
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:36
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.hy.serverside.mapper*")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
