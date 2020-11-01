package com.wph.springcloud.service.impl;

import com.wph.springcloud.dao.PaymentMapper;
import com.wph.springcloud.entities.Payment;
import com.wph.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    //@Autowired和@Resource都可以进行依赖注入
    @Resource
    private PaymentMapper paymentMapper;


    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
