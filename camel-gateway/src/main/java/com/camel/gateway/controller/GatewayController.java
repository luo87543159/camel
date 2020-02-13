package com.camel.gateway.controller;

import com.camel.gateway.service.impl.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;
    /**
     * 加载，同步网关配置
     * @return
     */
    @RequestMapping(value = "/synGatewayConfig")
    public String synGatewayConfig(){
        return gatewayService.loadAllLoadRoute();
    }
}
