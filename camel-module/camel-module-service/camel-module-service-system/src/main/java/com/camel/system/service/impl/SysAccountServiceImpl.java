package com.camel.system.service.impl;

import com.camel.system.domain.SysAccount;
import com.camel.system.mapper.SysAccountMapper;
import com.camel.system.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

@RestController
public class SysAccountServiceImpl implements SysAccountService {

    @Autowired
    private SysAccountMapper sysAccountMapper;

    @Override
    public SysAccount getByUserName(String username) {
        Example example = new Example(SysAccount.class);
        example.createCriteria().andEqualTo("username", username);
        return sysAccountMapper.selectOneByExample(example);
    }

}

