package com.xzx.hanfu.store_controller;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 开启定时任务
@EnableScheduling
//扫描mapper下的xml
@MapperScan("mapper.*")
//开启dubbo的注解
@EnableDubbo
//加载需要外部访问的包,否则404
@SpringBootApplication(scanBasePackages = {
        "service.*",
        "com.xzx.hanfu.store_controller.controller.*",
        "config",
        "mapper.*",
        "interfaces.*",
        "interceptor",
        "util",
        "timer"
})
public class StoreControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreControllerApplication.class, args);
    }

}
