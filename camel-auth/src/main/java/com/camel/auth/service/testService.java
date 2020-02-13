package com.camel.auth.service;

import com.camel.auth.openFeign.SystemOpenFeign;
import com.camel.system.dto.domain.SysAccount;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testService {

    @Autowired
    private SystemOpenFeign systemOpenFeign;

    @RequestMapping(value = "/bFeignToSystem1")
    public String bFeignToSystem1(){
        String result = systemOpenFeign.getByUserName("admin").getAccount();
        return  result;
    }
}
