package com.hwc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动部门微服务提供者
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker // 启用熔断机制（类路径下的某些接口已做了熔断后备 @HystrixCommand）
public class DeptProvider8001_App_Hystrix {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App_Hystrix.class, args);
    }
}
