package com.camel.b.service;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysAccount implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 登陆名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String account;

    /**
     * 最后登陆时间
     */
    private Date lastloginTime;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否过期
     */
    private Boolean accountNonExpired;

    /**
     * 是否锁定
     */
    private Boolean accountNonLocked;

    /**
     * 证书是否过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}
