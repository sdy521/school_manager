package com.study.study_manager.config;

import com.study.study_manager.security.LoginSuccessHandler;
import com.study.study_manager.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中比较
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("sdy").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
          auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        http.formLogin().loginPage("/login")
                //登录处理url
                .loginProcessingUrl("/j_spring_security_check")
                //登录时对应的用户名密码参数名
//                .usernameParameter("username").passwordParameter("password")
                .and()
                .authorizeRequests()
                .anyRequest()//任何请求登陆后都可以访问
                .authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对以下路径忽略过滤
        web.ignoring().antMatchers("/static/**", "/login");
    }

    // 处理密码加密解密逻辑
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
