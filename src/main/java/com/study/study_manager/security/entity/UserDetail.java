package com.study.study_manager.security.entity;

import com.study.study_manager.entity.LeftMenu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserDetail extends User {

    private List<LeftMenu> leftMenus;
    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public List<LeftMenu> getLeftMenus() {
        return leftMenus;
    }

    public void setLeftMenus(List<LeftMenu> leftMenus) {
        this.leftMenus = leftMenus;
    }
}
