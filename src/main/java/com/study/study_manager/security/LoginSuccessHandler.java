package com.study.study_manager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // Authentication  封装认证信息
    // 登录方式不同，Authentication不同
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        logger.info("MyAuthenticationSuccessHandler login success!");
    }
}
