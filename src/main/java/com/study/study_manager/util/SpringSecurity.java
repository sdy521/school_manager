package com.study.study_manager.util;

import com.study.study_manager.security.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurity {
    public static SysUser getSysUser(){
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return sysUser;
    }
}
