package com.camel.b.openFeign;

import com.camel.b.service.SysAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("camel-module-system")
public interface SystemOpenFeign {
    @GetMapping(value = "/abc")
    String abc();

    /**
     * 根据username 获取用户的信息
     * @param username
     * @return
     */
    @PostMapping(value = "/getByUserName")
    SysAccount getByUserName(@RequestParam("username") String username);
}
