package com.camel.auth.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface TokenService {

    /**
     * 显示令牌列表
     * @return
     */
    @GetMapping(value = "/oauth/tokens")
    List<String> getTokens();

    /**
     * 撤销访问令牌
     * @param tokenId
     * @return
     */
    @PostMapping(value = "/oauth/tokens/revoke/{tokenId:.*}")
    String revokeToken(@PathVariable String tokenId);


}
