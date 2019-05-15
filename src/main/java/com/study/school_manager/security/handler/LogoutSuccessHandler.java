package com.study.school_manager.security.handler;


import com.study.school_manager.core.log.LogFactory;
import com.study.school_manager.core.log.LogManager;
import com.study.school_manager.core.system.LoginType;
import com.study.school_manager.security.entity.UserDetail;
import com.study.school_manager.util.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by admin on 2017/6/19.
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler{

    @Override
    public void setDefaultTargetUrl(String defaultTargetUrl) {
        super.setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.getPrincipal()!=null&&authentication.getPrincipal() instanceof UserDetail){
            UserDetail userDetail = (UserDetail)authentication.getPrincipal();
            //记录登出日志
            LogManager.execute(LogFactory.loginlog(userDetail.getUsername(),userDetail.getType(), HttpUtil.getIp(), LoginType.LOGOUT));
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}
