package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\tgood!!!";
    }

    //访问异常
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {


        int age = 10/0;//异常情况
        int timenumber = 2000; //设置超时要大于上面设置的3000ms
        try {
            TimeUnit.MILLISECONDS.sleep(timenumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OutTime,id:"+id+"\t耗时(秒):"+timenumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler,id:"+id+"\t"+"8001T__T系统忙或错误！请稍后再试！";
    }

     /**
     *结论：服务超时以及服务异常都会做服务降级（兜底），兜底方法为：paymentInfo_TimeOutHandler
    */

}
