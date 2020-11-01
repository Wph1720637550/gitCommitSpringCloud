package com.wph.springcloud.service;

import com.wph.springcloud.entities.CommonResult;
import com.wph.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//对应Eureka网站上的微服务的名称
public interface PaymentFeignService {

    //直接去服务端spring-provider-payment8001找（控制器里找，也可以去服务接口找，但是老师用的控制器更加的干脆）
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
