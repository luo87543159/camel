package com.camel.system.common;

import com.camel.system.dto.domain.SysMenu;
import com.camel.system.dto.domain.TreeMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree {

    /**
     * 根据获取的meun集合，构建成tree结构
     * @param menuList 菜单集合
     * @return
     */
    public static  List<TreeMenu> menuTree(List<SysMenu> menuList) {
        //返回的菜单树
        List<TreeMenu> rootTreeMenu = new ArrayList<>();
        menuList.forEach(menu ->{
            // pid 为0 的是根菜单
            if("0".equals(menu.getPId())){
                TreeMenu treeMenu=getTreeMenu(menu);
                rootTreeMenu.add(treeMenu);
            }
        });

        // 遍历找到二级菜单（根菜单的id和所有菜单中的pid比较）
        rootTreeMenu.forEach(rootMenu->{
            rootMenu.setChildren(getChildren(rootMenu.getMenuId(),menuList));
        });
        return rootTreeMenu;
    }

    /**
     * 递归获取下级菜单
     * @param pid
     * @return
     */
    private static List<TreeMenu> getChildren(String pid,List<SysMenu> menuList) {
        //子菜单列表
        List<TreeMenu> childrenList = new ArrayList<>();
        menuList.forEach(menu ->{
            // pid 为0 的是根菜单
            if(pid.equals(menu.getPId())){
                TreeMenu treeMenu=getTreeMenu(menu);
                childrenList.add(treeMenu);
            }
        });
        //遍历 递归获取子菜单
        childrenList.forEach(childrenMenu ->{
            childrenMenu.setChildren(getChildren(childrenMenu.getMenuId(),menuList));
        });
        //递归出口  childrenList长度为0
        if (childrenList.size() == 0) return new ArrayList<>();
        return childrenList;
    }

    private static TreeMenu getTreeMenu(SysMenu menu) {
        TreeMenu treeMenu = new TreeMenu();
        Map<String,Object> metaMap = new HashMap<>();

        treeMenu.setMenuId(menu.getId());
        treeMenu.setPath(menu.getPath());
        treeMenu.setComponent(menu.getUrl());
        treeMenu.setName(menu.getResources());
        metaMap.put("title", menu.getTitle());
        metaMap.put("icon", menu.getIcon());
        treeMenu.setMeta(metaMap);

        return treeMenu;
    }


}
