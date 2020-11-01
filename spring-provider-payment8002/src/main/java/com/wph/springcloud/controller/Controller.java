package com.wph.springcloud.controller;

import com.wph.springcloud.entities.CommonResult;
import com.wph.springcloud.entities.Payment;
import com.wph.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class Controller {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")    //从yml中读取数据
    private String serverPort;

    //作用是对应注册进入eureka里面的微服务，可以通过服务发现来获得该服务的信息
    @Resource
    //用的是org.springframework.cloud.client.discovery.DiscoveryClient;
    private DiscoveryClient discoveryClient;

    //知识点：一般浏览器无法支持post请求在路径中模拟，需要用postman来模拟这个请求，非常好的一个软件
    @PostMapping(value = "/payment/create")
    //必须加@RequestBody,不然消费者调用数据无法插入到数据库
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入结果：" + result);

        if (result > 0){
            return new CommonResult(200,"插入数据库成功，serverPort：" + serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if (payment != null){
            return new CommonResult(200,"查询成功，serverPort：" + serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录，查询ID: " + id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();//对外暴露的微服务名称
        for (String element : services){
            log.info("******element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");//获得此微服务名称下的全部实例
        for (ServiceInstance serviceInstance : instances){
            log.info(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" +serviceInstance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
