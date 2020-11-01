package com.wph.springcloud.controller;

import com.wph.springcloud.entities.CommonResult;
import com.wph.springcloud.entities.Payment;
import com.wph.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/feign/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客户端一般默认等待一秒钟，此子设置等待3秒钟(在spring-provider-payment8001中),那么此时就会报错
        //所以需要协调好这个超时的时间，在yml配置文件中（但是最底层是由ribbon来做的，和负载均衡一样）
        return paymentFeignService.paymentFeignTimeout();
    }

}
