package com.camel.gateway.config;

import com.camel.gateway.service.impl.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动自动加载网管配置
 */
@Component
public class synGatewayConfig implements CommandLineRunner {

    @Autowired
    private GatewayService gatewayService;

    @Override
    public void run(String... args) throws Exception {
        try {
            String s = gatewayService.loadAllLoadRoute();
            System.out.println("启动加载网管配置" + s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
