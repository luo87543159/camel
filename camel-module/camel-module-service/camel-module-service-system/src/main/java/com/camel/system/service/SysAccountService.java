package com.camel.system.service;

import com.camel.system.domain.SysAccount;
import org.springframework.web.bind.annotation.PostMapping;


public interface SysAccountService {

    /**
     * 根据username 获取用户的信息
     *
     * @param username
     * @return
     */
    @PostMapping(value = "/getByUserName")
    SysAccount getByUserName(String username);


}

