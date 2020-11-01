package com.wph.springcloud.service;

import com.wph.springcloud.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
