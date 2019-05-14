package com.study.school_manager.util;

import com.study.school_manager.security.entity.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.util.Assert;

import java.util.List;

public class SpringSecurity {
    public static UserDetail getSysUser(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetail;
    }

    /**
     * 强制踢出指定用户
     */
    public static void kickOut(Object principal) {
        Assert.notNull(principal, "principal must not be null");
        SessionRegistry sessionRegistry = SpringContextUtil.getBean(SessionRegistry.class);
        List<SessionInformation> sessions =  sessionRegistry.getAllSessions(principal, false);
        sessions.forEach(SessionInformation::expireNow);
    }
}
