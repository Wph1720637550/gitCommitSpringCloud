package com.wph.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/******************切记，此配置类不能被@ComponentScan扫描，所以不能和主类放在一个包下，因为@SpringBootApplication包含@ComponentScan********************************************************/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();//定义负载均衡策略为随机（默认轮询）
    }
}
