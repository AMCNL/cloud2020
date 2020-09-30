package com.atguigu.myrlue;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机,注意单独建包和主启动类所在包以及子包隔离
    }
}
