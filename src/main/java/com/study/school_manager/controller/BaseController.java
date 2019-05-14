package com.study.school_manager.controller;

import com.study.school_manager.core.Result;
import com.study.school_manager.entity.LeftMenu;
import com.study.school_manager.security.entity.UserDetail;
import com.study.school_manager.util.SpringSecurity;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseController {

    protected static final Result OK = new Result();


    public List<LeftMenu> getMenus(String domain){
        UserDetail user = SpringSecurity.getSysUser();
        List<LeftMenu> menus = user.getLeftMenus();
        clearActive(menus);
        for(LeftMenu leftMenu:menus){
            leftMenu.setActive(activeMenu(leftMenu,domain));
        }
        //排序
        Collections.sort(menus, Comparator.comparing(LeftMenu::getSort));
        return menus;
    }
    private void clearActive(List<LeftMenu> menus) {
        for (LeftMenu menu : menus) {
            menu.setActive(false);
            if (!CollectionUtils.isEmpty(menu.getChildren())) {
                clearActive(menu.getChildren());
            }
        }
    }
    private boolean activeMenu(LeftMenu menu, String domain) {
        //如果是父级菜单
        if ("#".equals(menu.getUrl())) {
            List<LeftMenu> children = menu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                Collections.sort(children,Comparator.comparing(LeftMenu::getSort));
                for (LeftMenu child : children) {
                    if (activeMenu(child, domain)) {
                        return true;
                    }
                }
            }
        } else {
            if (ArrayUtils.contains(menu.getUrl().split("/"), domain)) {
                menu.setActive(true);
                return true;
            }
        }
        return false;
    }
}
