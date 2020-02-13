package com.camel.system.dto.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_log")
public class SysLog implements Serializable {
    /**
     * ID
     */
    @Column(name = "ID")
    private String id;

    /**
     * 接口地址
     */
    @Column(name = "`NAME`")
    private String name;

    /**
     * 访问人IP
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 访问人ID 0:未登录用户操作
     */
    @Column(name = "ACCOUNT_ID")
    private String accountId;

    /**
     * 访问状态
     */
    @Column(name = "`STATUS`")
    private Integer status;

    /**
     * 接口执行时间
     */
    @Column(name = "execute_time")
    private String executeTime;

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
