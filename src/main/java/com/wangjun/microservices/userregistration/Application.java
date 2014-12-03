package com.wangjun.microservices.userregistration;

import org.springframework.boot.SpringApplication;

import com.wangjun.microservices.userregistration.config.CoreConfig;

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(CoreConfig.class, args);
    }
}
