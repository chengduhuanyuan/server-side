package com.hy.serverside;

import com.hy.serverside.config.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.hy.serverside.config",
        "com.hy.serverside.controller",
        "com.hy.serverside.service"})
public class ServerSideApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServerSideApplication.class, args);
        new SpringApplicationBuilder().sources(ServerSideApplication.class).listeners(new MyApplicationListener()).run(args);
    }

}
