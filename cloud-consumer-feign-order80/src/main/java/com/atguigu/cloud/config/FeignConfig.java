package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        //Feign默认配置是不走重试策略的
        return Retryer.NEVER_RETRY;

        //最大请求次数是3，初始间隔时间为100ms，重试间最大间隔时间为1s
//        return new Retryer.Default(100,3,3);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
