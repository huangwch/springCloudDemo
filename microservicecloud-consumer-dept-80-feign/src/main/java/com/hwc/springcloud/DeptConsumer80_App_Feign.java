package com.hwc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hwc.springcloud"})
public class DeptConsumer80_App_Feign {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App_Feign.class, args);
    }
}
