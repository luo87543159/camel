package com.camel.system.service.impl;

import com.camel.system.service.SysService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysServiceImpl implements SysService {

    @Value("${server.port}")
    private String servicePort;

    public String abc(){
        return "我是SYS模块,端口号是：" + servicePort;
    }
}
