package com.hy.serverside.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @ClassName: ApplicationContextHolder
 * @Description: TODO 容器注册bean
 * @Author: Kaiser
 * @Date: 2019/4/16 10:08
 * @Version: 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T>T getBean(String name){
        return (T)applicationContext.getBean(name);
    }

    public static <T>T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
