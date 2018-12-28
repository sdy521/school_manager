package com.study.study_manager.security.handler;

import com.study.study_manager.entity.LeftMenu;
import com.study.study_manager.entity.Menu;
import com.study.study_manager.security.entity.UserDetail;
import com.study.study_manager.service.MenuService;
import com.study.study_manager.util.Constans;
import com.study.study_manager.util.SpringSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Resource
    private MenuService menuService;
    @Override
    public void setDefaultTargetUrl(String defaultTargetUrl) {
        super.setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetail user = SpringSecurity.getSysUser();
        //获取所有能看到的菜单
        List<Menu> all = new ArrayList<>();
        if(Constans.ROOT.equals(user.getUsername())){
            all = menuService.selectAll();
        }
        //构造树
        List<LeftMenu> leftMenus = getLeftMenu(all);
        user.setLeftMenus(leftMenus);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public List<LeftMenu> getLeftMenu(List<Menu> all){
        List<LeftMenu> leftMenu = new ArrayList<>();
        for(Menu menu:all){
            if(StringUtils.isEmpty(menu.getPcode())){
                LeftMenu firstMenu = new LeftMenu();
                firstMenu.setName(menu.getName());
                firstMenu.setUrl(menu.getUrl());
                firstMenu.setCode(menu.getCode());
                leftMenu.add(firstMenu);
            }
        }
        for(LeftMenu firstMenu:leftMenu){
            List<LeftMenu> children = new ArrayList<>();
            for(Menu menu2:all){
                if(menu2.getPcode().equals(firstMenu.getCode())){
                    LeftMenu secondMenu = new LeftMenu();
                    secondMenu.setName(menu2.getName());
                    secondMenu.setUrl(menu2.getUrl());
                    secondMenu.setCode(menu2.getCode());
                    secondMenu.setPcode(menu2.getPcode());
                    children.add(secondMenu);
                }
            }
            firstMenu.setChildren(children);
        }
        return leftMenu;
    }
}
