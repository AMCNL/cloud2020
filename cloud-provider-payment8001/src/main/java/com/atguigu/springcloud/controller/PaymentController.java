package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果："+result);

        if(result > 0 ){
            return new CommonResult(200,"插入成功",payment);
        }else {
            return new CommonResult(444,"插入失败",null);
        }

    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){//@PathVariable这个注解可以把@GetMapping(value = "/payment/get/{id}")中id的值进行绑定
         Payment payment= paymentService.getPaymentById(id);
        log.info("****查询结果****："+payment+":hello热部署");

        if(payment != null ){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(444,"查询"+id+"失败,无对应记录",null);
        }

    }
}
