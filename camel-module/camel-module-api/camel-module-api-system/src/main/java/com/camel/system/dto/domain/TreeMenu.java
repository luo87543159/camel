package com.camel.system.dto.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形菜单结构
 */
@Data
public class TreeMenu implements Serializable {
    private String menuId;
    private String path;
    private String component;
    private String name;
    private Map<String, Object> meta;
    private List<TreeMenu> children;
}
