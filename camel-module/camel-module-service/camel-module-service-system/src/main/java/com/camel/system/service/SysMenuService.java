package com.camel.system.service;

import com.camel.system.domain.SysMenu;
import com.camel.system.dto.domain.TreeMenu;
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

    /**
     * 根据获取的meun集合，构建成tree结构
     * @param menuList 菜单集合
     * @return
     */
    List<TreeMenu> getTreeMenuByMenu(List<SysMenu> menuList);
}
