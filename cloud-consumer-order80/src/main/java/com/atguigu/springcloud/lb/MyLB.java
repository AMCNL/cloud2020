package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date:2020/10/6
 * Author:Lynn.cn.Li
 * Desc:
 */
@Component
public class MyLB implements LoadBalance{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getAndIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current+1;

        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第"+next+"次访问");
        return next;
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
