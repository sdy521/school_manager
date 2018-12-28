package com.study.study_manager.controller;

import com.study.study_manager.entity.LeftMenu;
import com.study.study_manager.security.entity.UserDetail;
import com.study.study_manager.util.SpringSecurity;

import java.util.List;

public class BaseController {

    public List<LeftMenu> getMenus(){
        UserDetail user = SpringSecurity.getSysUser();
        List<LeftMenu> menus = user.getLeftMenus();
        return menus;
    }
}
