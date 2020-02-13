package com.camel.system.mapper;

import com.camel.system.domain.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {
    /**
     * 根据用户获取角色
     * @param accountId
     * @return
     */
    List<SysRole> getRolesByUserName(@Param("accountId") String accountId);
}
