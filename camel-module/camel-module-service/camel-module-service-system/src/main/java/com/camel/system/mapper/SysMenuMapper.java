package com.camel.system.mapper;

import com.camel.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {
    List<SysMenu> selectByAccountId(@Param("accountId") String accountId);
}