package com.camel.system.service.impl;

import com.camel.system.domain.SysMenu;
import com.camel.system.mapper.SysMenuMapper;
import com.camel.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getByAccountId(String id) {
        return sysMenuMapper.selectByAccountId(id);
    }
}
