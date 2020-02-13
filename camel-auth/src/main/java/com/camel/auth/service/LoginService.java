package com.camel.auth.service;

import com.camel.common.core.constant.JsonResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

public interface LoginService {

    //    @GetMapping("/oauth/token")
//    JsonResult getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;

    /**
     * 重写/oauth/token api
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/oauth/token")
    JsonResult postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;

    @GetMapping(value = "/user/info")
    JsonResult info(@RequestParam("token") String value);

    @PostMapping(value = "/user/logout")
    JsonResult logout();
}
