package com.camel.system.dto.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 父ID
     */
    @Column(name = "P_ID")
    private String pId;

    /**
     * views下面的vue文件
     */
    @Column(name = "`PATH`")
    private String path;

    /**
     * 资源地址
     */
    @Column(name = "URL")
    private String url;

    /**
     * 资源编码
     */
    @Column(name = "RESOURCES")
    private String resources;

    /**
     * 资源名称
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 资源级别
     */
    @Column(name = "`LEVEL`")
    private Integer level;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 资源图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 类型 menu、button
     */
    @Column(name = "`TYPE`")
    private String type;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

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
