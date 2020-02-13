package com.camel.b;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class BApplication {
    public static void main(String[] args) {
        SpringApplication.run(BApplication.class, args);
    }

    /**
     * 注入RestTemplate 模板到spring
     * @return
     */
    @Bean
    @LoadBalanced // 使用Ribbon 实现本地负载均衡 需要加入这个注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
