package com.study.study_manager.config;

import com.study.study_manager.security.handler.LoginSuccessHandler;
import com.study.study_manager.security.handler.LogoutSuccessHandler;
import com.study.study_manager.security.handler.MyInvalidSessionStrategy;
import com.study.study_manager.security.handler.MySessionInformationExpiredStrategy;
import com.study.study_manager.security.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        http.formLogin().loginPage("/login")
                //登录处理url
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(loginSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .authorizeRequests()
                .anyRequest()//任何请求登陆后都可以访问
                .authenticated()
                //失效处理政策
                .and().sessionManagement().invalidSessionStrategy(invalidSessionStrategy())
                //单用户登录
                .maximumSessions(1).sessionRegistry(getSessionRegistry())
                //过期处理政策
                .expiredSessionStrategy(mySessionInformationExpiredStrategy());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对以下路径忽略过滤
        web.ignoring().antMatchers("/static/**", "/login");
    }

    /***
     * 处理密码加密解密逻辑
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 单用户登录
     * @return
     */
    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }

    /***
     * 登录成功处理器
     * @return
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        loginSuccessHandler.setDefaultTargetUrl("/main");
        return loginSuccessHandler;
    }
    /**
     * 登出成功处理器
     *
     * @return LogoutSuccessHandler
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/login?logout");
        return logoutSuccessHandler;
    }

    @Bean
    public MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy() {
        return new MySessionInformationExpiredStrategy("/login?expired");
    }

    /**
     * {@see MyInvalidSessionStrategy}
     *
     * @return  InvalidSessionStrategy
     */
    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new MyInvalidSessionStrategy("/login?expired");
    }
}
