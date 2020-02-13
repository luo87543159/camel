package com.camel.system.mapper;

import com.camel.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysMenuMapper extends MyMapper<SysMenu> {
    List<SysMenu> selectByAccountId(@Param("accountId") String accountId);
}
