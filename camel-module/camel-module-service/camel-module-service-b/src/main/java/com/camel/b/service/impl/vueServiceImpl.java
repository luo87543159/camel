package com.camel.b.service.impl;

import com.camel.b.service.vueService;
import com.camel.common.core.constant.JsonResult;
import com.camel.common.core.constant.ResultTool;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class vueServiceImpl implements vueService {
    @Override
    public JsonResult info() {
        Map<String,Object> abc = new HashMap<>();
        String[] str={"admin"};
        abc.put("roles", str);
        abc.put("introduction","I am a super administrator");
        abc.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        abc.put("name","Super Admin");
        return ResultTool.success(abc);
    }

    @Override
    public JsonResult login() {
        Map<String,Object> abc = new HashMap<>();
        abc.put("access_token", "0a907e99-df49-4a97-8bef-710bbe07e919");
        abc.put("token_type", "bearer");
        abc.put( "expires_in", 22783);
        abc.put("scope", "app");
        return ResultTool.success(abc);
    }

    @Override
    public JsonResult logout() {
        return ResultTool.success("success");
    }
}
