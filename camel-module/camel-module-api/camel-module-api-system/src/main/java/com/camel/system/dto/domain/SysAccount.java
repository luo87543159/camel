package com.camel.system.dto.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_account")
public class SysAccount implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 登陆名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`PASSWORD`")
    private String password;

    /**
     * 用户名
     */
    @Column(name = "ACCOUNT")
    private String account;

    /**
     * 头像地址
     */
    @Column(name = "AVATAR")
    private String avatar;

    /**
     * 最后登陆时间
     */
    @Column(name = "LASTLOGIN_TIME")
    private Date lastloginTime;

    /**
     * 是否可用
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    /**
     * 是否过期
     */
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;

    /**
     * 是否锁定
     */
    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;

    /**
     * 证书是否过期
     */
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;

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
