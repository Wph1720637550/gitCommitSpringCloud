package com.wph.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //开启负载均衡功能，使RestTemplate具有负载均衡的能力   默认的是一人来一下，轮询，即这次是你来干活，下次是我
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
