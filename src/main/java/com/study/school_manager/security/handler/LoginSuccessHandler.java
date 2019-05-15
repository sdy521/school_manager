package com.study.school_manager.security.handler;

import com.study.school_manager.core.log.LogFactory;
import com.study.school_manager.core.log.LogManager;
import com.study.school_manager.core.system.LoginType;
import com.study.school_manager.dao.RolesDao;
import com.study.school_manager.entity.LeftMenu;
import com.study.school_manager.entity.Menu;
import com.study.school_manager.entity.Role;
import com.study.school_manager.security.entity.UserDetail;
import com.study.school_manager.service.MenuService;
import com.study.school_manager.core.system.Constants;
import com.study.school_manager.util.HttpUtil;
import com.study.school_manager.util.SpringSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Value("${websocket.url}")
    private String websocketUrl;
    @Resource
    private MenuService menuService;
    @Resource
    private RolesDao rolesDao;
    @Override
    public void setDefaultTargetUrl(String defaultTargetUrl) {
        super.setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetail user = SpringSecurity.getSysUser();
        //记录登陆日志
        LogManager.execute(LogFactory.loginlog(user.getUsername(),user.getType(), HttpUtil.getIp(), LoginType.SUCCESS));
        //初始化密码
        if(new BCryptPasswordEncoder().matches(Constants.DEFAULT_PASSWORD,user.getPassword())){
            getRedirectStrategy().sendRedirect(request,response,"/initPassword");
        }else {
            //获取对应菜单权限
            Role role = rolesDao.getMenuRoles(user.getUsername());
            //获取所有能看到的菜单
            List<Menu> all = new ArrayList<>();
            if(Constants.ADMIN.equals(role.getName())){
                Menu param = new Menu();
                param.setDeleted(false);
                all = menuService.select(param);
            }else if(Constants.TEACHER.equals(role.getName())){
                Menu menu = new Menu();
                menu.setType(1);
                all = menuService.select(menu);
            }else if(Constants.STUDENTS.equals(role.getName())){
                Menu menu = new Menu();
                menu.setType(2);
                all = menuService.select(menu);
            }
            //构造树
            List<LeftMenu> leftMenus = getLeftMenu(all);
            user.setLeftMenus(leftMenus);
            request.getSession().setAttribute("leftname",user.getUsername());
            request.getSession().setAttribute("userImg",user.getImg());
            request.getSession().setAttribute("websocketUserId",user.getId());
            request.getSession().setAttribute("websocketUrl",websocketUrl);
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    public List<LeftMenu> getLeftMenu(List<Menu> all){
        List<LeftMenu> leftMenu = new ArrayList<>();
        for(Menu menu:all){
            if(StringUtils.isEmpty(menu.getPcode())){
                LeftMenu firstMenu = new LeftMenu();
                firstMenu.setName(menu.getName());
                firstMenu.setUrl(menu.getUrl());
                firstMenu.setCode(menu.getCode());
                firstMenu.setIcon(menu.getIcon());
                firstMenu.setSort(menu.getSort());
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
                    secondMenu.setIcon(menu2.getIcon());
                    secondMenu.setSort(menu2.getSort());
                    children.add(secondMenu);
                }
            }
            firstMenu.setChildren(children);
        }
        return leftMenu;
    }
}
