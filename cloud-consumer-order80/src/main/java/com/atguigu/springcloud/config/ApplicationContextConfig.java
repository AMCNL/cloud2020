package com.atguigu.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced  //开启微服务负载均衡注解配置(非自定义负载均衡要使用)
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

/**
*
 *  @Bean
    public IRule getIReule(){ //通过获取一个IRule对象，
        return  new RandomRule();  //达到的目的，用我们重新选择的随机，替代默认的轮训方式
    }*/


}
