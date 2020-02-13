package com.camel.auth.service.impl;

import com.camel.auth.openFeign.SystemOpenFeign;
import com.camel.auth.service.LoginService;
import com.camel.common.core.constant.JsonResult;
import com.camel.common.core.constant.ResultTool;
import com.camel.system.dto.domain.SysAccount;
import com.camel.system.dto.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
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
        System.out.println(sysAccount);
        //获取用户的角色
        List<String> roleNameList = new ArrayList<>();
        List<SysRole> roles = systemOpenFeign.rolesByAccountId("1");
        roles.forEach(role ->{
            roleNameList.add(role.getName());
        });
        Map<String,Object> data = new HashMap<>();
        String[] str={"admin"};
        data.put("roles", roleNameList);
        //data.put("introduction","I am a super administrator");
        data.put("avatar", sysAccount.getAvatar());
        data.put("name",sysAccount.getUsername());
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
