package com.camel.auth.service.impl;


import com.camel.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class TokenServiceImpl implements TokenService {


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices tokenServices;
    /**
     * 出于管理员的目的，我们还设置了一种查看当前有效令牌的方法。
     * 我们将在控制器中访问TokenStore，并检索指定客户端ID的当前存储的令牌：
     * @return
     */
    @Override
    public List<String> getTokens() {
        List<String> tokenValues = new ArrayList<String>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("client");

        if (tokens!=null) {
            for (OAuth2AccessToken token:tokens){
                tokenValues.add(token.getValue());
            }
        }
        return tokenValues;
    }

    /**
     * 为了使令牌无效，我们将使用ConsumerTokenServices接口中的revokeToken（） API ：
     * 这是一个非常敏感的操作，因此我们要么只在内部使用它，要么我们应该格外小心，以确保适当的安全性。
     * @param tokenId
     * @return
     */
    @Override
    public String revokeToken(String tokenId) {
        tokenServices.revokeToken(tokenId);
        return tokenId;
    }


}
