package com.camel.b.service.impl;

import com.camel.b.openFeign.SystemOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BService {

    @Autowired
    private SystemOpenFeign systemOpenFeign;

    @RequestMapping(value = "/bFeignToSystem")
    public String bFeignToSystem(){
        String result = systemOpenFeign.abc();
        return "我是a服务调用system，结果是："+ result;
    }

    @RequestMapping(value = "/bFeignToSystem1")
    public String bFeignToSystem1(){
        String result = systemOpenFeign.getByUserName("admin").getAccount();
        return "我是a服务调用system，结果是："+ result;
    }
}
