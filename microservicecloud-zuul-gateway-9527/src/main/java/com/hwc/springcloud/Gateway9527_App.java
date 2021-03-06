package com.hwc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Gateway9527_App {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9527_App.class, args);
    }
}
