package com.camel.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;


/**
 *
 */
@Component
public class TokenGlobalFilter implements GlobalFilter {
    @Value("${server.port}")
    private String serverPort;

    //不需要验证的路径
    private String[] skipAuthUrls = {"/oauth/token","/user/logout"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        System.out.println(url);
        //跳过不需要验证的路径
        if (null !=skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }

        //获取url中的token
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            //如果为空
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            String msg = "token not is null";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        // 在请求头中存放serverPort serverPort
        ServerHttpRequest request = exchange.getRequest().mutate().header("serverPort", this.serverPort).build();
        return chain.filter(exchange.mutate().request(request).build());
    }
}
