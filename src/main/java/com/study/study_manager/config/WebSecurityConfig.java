package com.study.study_manager.config;

import com.study.study_manager.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//（主要配置文件）

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()//该方法所返回的对象的方法来配置请求级别的安全细节
        .antMatchers("/login").permitAll()//登录界面不拦截
        .antMatchers("/api/**").permitAll()//调用api不需要拦截
        .antMatchers(HttpMethod.POST, "/checkLogin").permitAll()// 对于登录路径不进行拦截
        .and().formLogin()//配置登录界面
        .loginPage("/login")//登录界面的访问路径
        .loginProcessingUrl("/checkLogin")//登录界面下表单提交的路径
        .failureForwardUrl("/login?error=true")//登录失败后的路径，为了给客户提示
        .defaultSuccessUrl("/index");//登录成功后默认跳转的路径
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //对以下路径忽略过滤
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/login");
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
