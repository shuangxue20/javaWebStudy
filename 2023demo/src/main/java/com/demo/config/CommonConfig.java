package com.demo.config;


import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {//该类用于把第三方的bean交给IOC容器管理

    //@Bean //将方法返回值交给IOC，通过name和value属性来指定bean名称，如果没有默认是方法名
    @Bean
    public SAXReader saxReader(){
        return new SAXReader();
    }
}
