package com.study.study_manager.security.entity;

import org.springframework.security.core.GrantedAuthority;

public class MGrantedAuthority implements GrantedAuthority {

    private String authority;

    public MGrantedAuthority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}