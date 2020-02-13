package com.camel.gateway.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.camel.gateway.mapper.GatewayRoutesMapper;
import com.camel.gateway.service.GatewayRoutesService;
@Service
public class GatewayRoutesServiceImpl implements GatewayRoutesService{

    @Resource
    private GatewayRoutesMapper gatewayRoutesMapper;

}
