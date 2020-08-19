package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //设置8001支付访问路径
    //public static final String PAYMENT_URL = "http://localhost:8001";//单机版使用，集群版不能写死

    //集群版
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";//集群版要写微服务名称


    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/cosumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("***插入成功***");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/cosumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("***查询成功***");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }






}

