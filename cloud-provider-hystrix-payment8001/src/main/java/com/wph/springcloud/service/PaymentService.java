package com.wph.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 为省时间，就不写接口了，实际中需要规范接口
 *
 */

@Service
public class PaymentService {

    /*****************************************fallback（服务降级）*************************************************/
    /**
     * 正常访问肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK, id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //服务降级，无论运行还是超市异常，兜底的方法都可以处理
    //此方法出错了由paymentInfo_TimeOutHandler方法兜底
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //设置自身调用时的时间峰值，峰值内可以正常运行，超过了需要有兜底方法处理，作服务降级fallback
            //这里的单位是毫秒
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")  //3秒钟以内就是正常的业务逻辑
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeOut = 3;

        //int age = 10/0;

        //暂停三秒钟，注意此时的单位是秒
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_TimeOut, id:  " + id + "\t"
                + "O(∩_∩)O哈哈~" + "耗时（秒）: " +timeOut;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "  8001系统繁忙或运行报错，请稍后再试, id:  " + id + "\t"
                + "o(╥﹏╥)o";

    }


    /*****************************************CircuitBreaker（服务熔断）*************************************************/
    /*
    * Volume:音量、频率；     Threshold：门槛；
    * */
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数  （默认20次）
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围（时间窗口期），单位毫秒   （默认10秒）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸   （默认失败率50%）
    })      //默认段路器开启5秒之后进入半开状态

    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();  //等价于UUID.randomUUID().toString();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }




}
