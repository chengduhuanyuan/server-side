package com.hy.serverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        SpringApplication.run(ServerSideApplication.class, args);
    }

}
