package com.study.study_manager.config;

import com.study.study_manager.security.LoginSuccessHandler;
import com.study.study_manager.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//配置Spring Security
@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        //定义哪些url需要保护，哪些url不需要保护
        http.authorizeRequests()
                //所有的请求都需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //登录处理url
                .loginProcessingUrl("/j_spring_security_check")
                //定义当需要用户登录时候，转到的登录页面
                .loginPage("/login")
                //登录时对应的用户名密码参数名
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对以下路径忽略过滤
        web.ignoring().antMatchers("/static/**","/login");
    }

    /**
     * 登录成功处理器
     *
     * @return LoginSuccessHandler
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
//        loginSuccessHandler.setDefaultTargetUrl("/main");
        return loginSuccessHandler;
    }

}
