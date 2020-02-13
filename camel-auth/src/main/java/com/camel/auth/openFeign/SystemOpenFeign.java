package com.camel.auth.openFeign;

import com.camel.system.dto.domain.SysAccount;
import com.camel.system.dto.domain.SysMenu;
import com.camel.system.dto.domain.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("camel-module-system")
public interface SystemOpenFeign {
    /**
     * 根据username 获取用户的信息
     * @param username
     * @return
     */
    @PostMapping(value = "/getByUserName")
    SysAccount getByUserName(@RequestParam("username") String username);

    /**
     * 根据 sysAccountId 获取授权菜单集合
     * @param id
     * @return
     */
    @PostMapping(value = "/getByAccountId")
    List<SysMenu> getByAccountId(@RequestParam("id") String id);

    /**
     * 根据用户id获取角色
     * @param accountId
     * @return
     */
    @GetMapping(value = "/rolesByAccountId")
    List<SysRole>  rolesByAccountId(@RequestParam("accountId") String accountId);
}
