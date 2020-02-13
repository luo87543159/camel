package com.camel.system.service;

import com.camel.system.domain.SysMenu;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface SysMenuService {
    /**
     * 根据 sysAccountId 获取菜单集合
     * @param id
     * @return
     */
    @PostMapping(value = "/getByAccountId")
    List<SysMenu> getByAccountId(String id);
}
