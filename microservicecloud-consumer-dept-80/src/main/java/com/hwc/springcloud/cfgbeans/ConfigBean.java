package com.hwc.springcloud.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate() {
        /**
         * RestTemplate是访问Rest服务大的客户端模板工具集
         */
        return new RestTemplate();
    }
}