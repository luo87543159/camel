package com.camel.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.camel.auth.openFeign.SystemOpenFeign;
import com.camel.auth.service.LoginService;
import com.camel.common.core.constant.JsonResult;
import com.camel.common.core.constant.ResultTool;
import com.camel.system.common.MenuTree;
import com.camel.system.dto.domain.SysAccount;
import com.camel.system.dto.domain.SysMenu;
import com.camel.system.dto.domain.SysRole;
import com.camel.system.dto.domain.TreeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SystemOpenFeign systemOpenFeign;

    @Autowired
    private TokenEndpoint tokenEndpoint;


    @Autowired
    private TokenStore tokenStore;

    @Override
    public JsonResult postAccessToken(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @Override
    public JsonResult info(@RequestParam("token") String value) {

        //通过token获取authentication 在获取用户信息
        OAuth2AccessToken token = tokenStore.readAccessToken(value);
        OAuth2Authentication authentication = tokenStore.readAuthentication(token);

        String currentUser = authentication.getName();
        SysAccount sysAccount = systemOpenFeign.getByUserName(currentUser);

        //获取用户的角色
        List<String> roleNameList = new ArrayList<>();
        List<SysRole> roles = systemOpenFeign.rolesByAccountId(sysAccount.getId());
        roles.forEach(role ->{
            roleNameList.add(role.getCode());
        });

        //获取菜单
        List<SysMenu> sysMenus = systemOpenFeign.getByAccountId(sysAccount.getId());
        System.out.println(JSON.toJSON(sysMenus));
        List<TreeMenu> treeMenus = MenuTree.menuTree(sysMenus);
        System.out.println(JSON.toJSON(treeMenus));

        //构建data 数据
        Map<String,Object> data = new HashMap<>();
        data.put("roles", roleNameList);
        data.put("routers",treeMenus);
        data.put("avatar", sysAccount.getAvatar());
        data.put("name",sysAccount.getAccount());
        return ResultTool.success(data);
    }

    @Override
    public JsonResult logout() {
        return ResultTool.success("success");
    }


    /**
     * 自定义返回格式
     * @param accessToken
     * @return
     */
    private JsonResult custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
        data.put("access_token", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResultTool.success(data);
    }
}
