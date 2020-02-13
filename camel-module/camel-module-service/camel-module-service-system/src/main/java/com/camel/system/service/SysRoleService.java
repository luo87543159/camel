package com.camel.system.service;

import com.camel.system.domain.SysRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SysRoleService {

    /**
     * 根据用户id获取角色
     * @param accountId
     * @return
     */
    @GetMapping(value = "/rolesByAccountId")
    List<SysRole>  rolesByAccountId(@RequestParam("accountId") String accountId);
}
