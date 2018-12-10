package com.study.study_manager.config;

import com.study.study_manager.security.LoginSuccessHandler;
import com.study.study_manager.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

//配置Spring Security
@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        http. authorizeRequests()
                //所有的请求都需要认证
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                //登录处理url
                .loginProcessingUrl("/j_spring_security_check")
                //登录时对应的用户名密码参数名
                .usernameParameter("username").passwordParameter("password")
                //登录成功处理器
                .successHandler(loginSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对以下路径忽略过滤
        web.ignoring().antMatchers("/static/**","/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    /**
     * 密码加密
     *
     * @return  BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录成功处理器
     *
     * @return LoginSuccessHandler
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        loginSuccessHandler.setDefaultTargetUrl("/main");
        return loginSuccessHandler;
    }
}
