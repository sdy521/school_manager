package com.study.school_manager.security.service;

import com.study.school_manager.core.log.LogFactory;
import com.study.school_manager.core.log.LogManager;
import com.study.school_manager.core.system.Constants;
import com.study.school_manager.core.system.LoginType;
import com.study.school_manager.dao.RolesDao;
import com.study.school_manager.dao.UserDao;
import com.study.school_manager.entity.Menu;
import com.study.school_manager.security.entity.MGrantedAuthority;
import com.study.school_manager.entity.Role;
import com.study.school_manager.entity.User;
import com.study.school_manager.security.entity.UserDetail;
import com.study.school_manager.service.MenuService;
import com.study.school_manager.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Resource
    private RolesDao rolesDao;
    @Resource
    private MenuService menuService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆用户:" + username);
        User select = new User();
        select.setName(username);
        User user = userDao.selectOne(select);
        if (user == null) {
            logger.info("用户不存在 用户名={}"+username);
            throw new UsernameNotFoundException("该用户不存在 用户名={}" + username);
        }
        if(user.getEnable()==false){
            //登陆日志
            LogManager.execute(LogFactory.loginlog(username,user.getType(), HttpUtil.getIp(), LoginType.DISABLED));
            logger.info("用户被禁用，无法登陆 用户名={}"+username);
            throw new UsernameNotFoundException("用户被禁用，无法登陆 用户名={}"+username);
        }
        /*List<Role> roleList = rolesDao.getRoles(user.getId());
        Set<MGrantedAuthority> authorities = new HashSet<MGrantedAuthority>();
        if (roleList.size() != 0){
            for (Role role: roleList){
                authorities.add(new MGrantedAuthority(role.getName()));
                System.out.println(role.getName());
            }
        }*/
        //获取权限
        Collection<GrantedAuthority> grantedAuthorities = obtainGrantedAuthorities(user);
        UserDetail userDetail = new UserDetail(user.getName(),user.getPassword(),user.getEnable(),true,true,true,grantedAuthorities);
        userDetail.setId(user.getId());
        userDetail.setImg(user.getImg());
        userDetail.setType(user.getType());
        return userDetail;
    }

    private Set<GrantedAuthority> obtainGrantedAuthorities (
            User user) {
        List<Menu> menus;
        //如果是管理员
        Role role = rolesDao.getRoleByUserId(user.getId());
        if (Constants.ADMIN.equals(role.getName())) {
            menus = menuService.selectAll();
        } else {
            Menu menu = new Menu();
            menu.setType(user.getType());
            menus = menuService.select(menu);
            //增加都能访问的菜单
            List<Menu> notlimitMenus = menuService.selectNotLimit();
            menus.addAll(notlimitMenus);
        }
        Set<GrantedAuthority> authSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(menus)) {
            for (Menu menu : menus) {
                authSet.add(new SimpleGrantedAuthority("ROLE_" + menu.getCode()));
            }
        }
        return authSet;
    }
}
