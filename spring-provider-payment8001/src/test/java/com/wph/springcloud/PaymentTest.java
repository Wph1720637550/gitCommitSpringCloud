package com.wph.springcloud;



import com.wph.springcloud.dao.PaymentMapper;
import com.wph.springcloud.entities.Payment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
public class PaymentTest {

    @Resource
    PaymentMapper mapper;

    @Test
    public void testMapper(){
        Payment payment = mapper.getPaymentById(1L);
        System.err.println(payment);
    }

    @Test
    public void testMapper02(){
        Payment payment = mapper.getPayment(1L);
        System.err.println(payment);
    }

}
