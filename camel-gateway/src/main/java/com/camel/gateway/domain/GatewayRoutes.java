package com.camel.gateway.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "gateway_routes")
public class GatewayRoutes implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 路由ID
     */
    @Column(name = "ROUTE_ID")
    private String routeId;

    /**
     * 路由名称
     */
    @Column(name = "ROUTE_NAME")
    private String routeName;

    /**
     * 路由规则
     */
    @Column(name = "ROUTE_PATTEM")
    private String routePattem;

    /**
     * 路由类型（1.注册中心获取，2.什么地方获取）
     */
    @Column(name = "ROUTE_TYPE")
    private String routeType;

    /**
     * 转发地址
     */
    @Column(name = "ROUTE_URL")
    private String routeUrl;

    /**
     * 创建人
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}