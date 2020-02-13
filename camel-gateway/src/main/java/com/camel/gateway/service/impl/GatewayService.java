package com.camel.gateway.service.impl;

import com.camel.gateway.domain.GatewayRoutes;
import com.camel.gateway.mapper.GatewayRoutesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class GatewayService implements ApplicationEventPublisherAware {

    public static final String ROUTE_TYPE = "0";

    private ApplicationEventPublisher publisher;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private GatewayRoutesMapper gatewayRoutesMapper;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 加载所有配置
     * @return
     */
    public String loadAllLoadRoute(){
        List<GatewayRoutes> gatewayRoutes = gatewayRoutesMapper.selectAll();
        for (GatewayRoutes GatewayRoute : gatewayRoutes){
            loadRoute(GatewayRoute);
        }
        return "success";
    }

    public String loadRoute(GatewayRoutes gatewayRoutes){
        RouteDefinition definition = new RouteDefinition();
        Map<String, String> PredicateParams= new HashMap<>(8);
        PredicateDefinition predicate = new PredicateDefinition();
        FilterDefinition filterDefinition = new FilterDefinition();
        HashMap<String, String> filterParams = new HashMap<>(8);
        //如果配置路由type 为0 则从注册中心获取服务地址
        URI uri=null;
        if (ROUTE_TYPE.equals(gatewayRoutes.getRouteType())) {
            uri= UriComponentsBuilder.fromUriString("lb://"+ gatewayRoutes.getRouteUrl()+"/").build().toUri();
        }else {
            UriComponentsBuilder.fromHttpUrl(gatewayRoutes.getRouteUrl()).build().toUri();
        }


        //定义的路由唯一的id
        definition.setId(gatewayRoutes.getRouteId());
        predicate.setName("Path");
        //路由转发地址
        PredicateParams.put("pattern", gatewayRoutes.getRoutePattem());
        predicate.setArgs(PredicateParams);

        //名称是固定的，路径去前缀
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", "1");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Arrays.asList(predicate));
        definition.setFilters(Arrays.asList(filterDefinition));
        definition.setUri(uri);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
