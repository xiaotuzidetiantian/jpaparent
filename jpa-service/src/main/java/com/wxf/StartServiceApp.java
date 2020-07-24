package com.wxf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(StartServiceApp.class,args);
    }
}
