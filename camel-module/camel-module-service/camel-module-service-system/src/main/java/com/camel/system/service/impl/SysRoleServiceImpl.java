package com.camel.system.service.impl;

import com.camel.system.domain.SysRole;
import com.camel.system.mapper.SysRoleMapper;
import com.camel.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> rolesByAccountId(String accountId) {
        return sysRoleMapper.getRolesByUserName(accountId);
    }

}
