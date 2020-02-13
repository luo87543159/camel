package com.camel.b.service;

import com.camel.common.core.constant.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface vueService {
    @GetMapping(value = "/user/info")
    default JsonResult info(){
        return null;
    }

    @PostMapping(value = "/oauth/token")
    default JsonResult login(){
        return null;
    }

    @PostMapping(value = "/user/logout")
    default JsonResult logout(){
        return null;
    }
}
