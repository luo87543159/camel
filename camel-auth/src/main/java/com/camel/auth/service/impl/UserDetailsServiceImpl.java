package com.camel.auth.service.impl;

import com.camel.auth.openFeign.SystemOpenFeign;
import com.camel.system.dto.domain.SysAccount;
import com.camel.system.dto.domain.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现自定义用户认证的核心逻辑
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemOpenFeign systemOpenFeign;


    /**
     * loadUserByUsername(String username)的参数就是登录时提交的用户名，
     * 返回类型是一个叫UserDetails 的接口，需要在这里构造出他的一个实现类User
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*需要构造出 org.springframework.security.core.userdetails.User 对象并返回*/

        //获取用户的信息
        SysAccount sysAccount = systemOpenFeign.getByUserName(username);

        List<GrantedAuthority> grantedAuthorities= new ArrayList<>();

        if (sysAccount == null) {
            throw new UsernameNotFoundException("用户不存在");
        }else {
            //根据用户id 获取菜单集合
            List<SysMenu> menus = systemOpenFeign.getByAccountId(sysAccount.getId());

            //声明用户授权菜单
            menus.forEach(menu ->{
                if (menu != null && menu.getResources() !=null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getResources());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }
        //返回UserDetails 实现类
        return new User(sysAccount.getUsername(), sysAccount.getPassword(), sysAccount.getEnabled(), sysAccount.getAccountNonExpired(),sysAccount.getCredentialsNonExpired(), sysAccount.getAccountNonLocked(),grantedAuthorities);
    }

}
