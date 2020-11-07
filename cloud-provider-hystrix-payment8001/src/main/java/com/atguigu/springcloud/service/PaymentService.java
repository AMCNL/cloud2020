package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\tgood!!!";
    }

    //访问异常
    public String paymentInfo_TimeOut(Integer id) {

        int timenumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OutTime,id:"+id+"\t耗时(秒):"+timenumber;
    }

}
