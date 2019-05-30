package com.hy.serverside.config;

import com.hy.serverside.controller.WechatController;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName: MyApplicationListener
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/30 9:38
 * @Version: 1.0
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //springboot应用启动且未作任何处理（除listener注册和初始化）的时候发送ApplicationStartingEvent
        if (event instanceof ApplicationStartingEvent){
            System.out.println("ApplicationStarting");
            return;
        }
        //确定springboot应用使用的Environment且context创建之前发送这个事件
        if (event instanceof ApplicationEnvironmentPreparedEvent){
            System.out.println("ApplicationEnvironmentPrepared");
            return;
        }
        //context已经创建且没有refresh发送个事件
        if (event instanceof ApplicationPreparedEvent){
            System.out.println("ApplicationPrepared");
            return;
        }
        //context已经refresh且application and command-line runners（如果有） 调用之前发送这个事件
        if (event instanceof ApplicationStartedEvent){
            System.out.println("ApplicationStarted");
            return;
        }
        //application and command-line runners （如果有）执行完后发送这个事件，此时应用已经启动完毕
        if (event instanceof ApplicationReadyEvent){
            ApplicationContext context = ((ApplicationReadyEvent) event).getApplicationContext();
            System.out.println("ApplicationReadyEvent......");
            WechatController wechatController = context.getBean(WechatController.class);
            wechatController.saveAccessToken();
//            SystemInitService initService = context.getBean(SystemInitService.class);
//            initService.systemInit();
            return;
        }
        //应用启动失败后产生这个事件
        if (event instanceof ApplicationFailedEvent){
            System.out.println("ApplicationFailed....");
            return;
        }
    }
}
