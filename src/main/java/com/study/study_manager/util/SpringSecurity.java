package com.study.study_manager.util;

import com.study.study_manager.security.entity.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurity {
    public static UserDetail getSysUser(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userDetail;
    }
}
