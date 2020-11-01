package com.wph.springcloud.dao;

import com.wph.springcloud.entities.Payment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentMapper {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    @Select("select * from payment where id = #{id}")
    public Payment getPayment(@Param("id") Long id);

}
