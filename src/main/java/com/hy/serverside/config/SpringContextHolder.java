package com.hy.serverside.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextHolder
 * @Description: TODO 配置bean
 * @Author: Kaiser
 * @Date: 2019/4/16 10:42
 * @Version: 1.0
 */
@Component
@SuppressWarnings("all")
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
    private static void assertApplication(){
        if (SpringContextHolder.applicationContext == null){
            throw new RuntimeException("appliectionContext为空，查看是否注入SpringContextHolder");
        }
    }
    public static ApplicationContext getApplicationContext(){
        assertApplication();
        return applicationContext;
    }
    public static<T> T getBean(String name){
        assertApplication();
        return (T)applicationContext.getBean(name);
    }
    public static<T> T getBan(Class<T> clazz){
        assertApplication();
        return applicationContext.getBean(clazz);
    }
}
