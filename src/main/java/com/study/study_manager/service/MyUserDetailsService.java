package com.study.study_manager.service;

import com.study.study_manager.dao.SysUserRepository;
import com.study.study_manager.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;

/***
 * 我们需要重写UserDetailsService接口，然后实现该接口中的loadUserByUsername方法，
 * 通过该方法查询到对应的用户，这里之所以要实现UserDetailsService接口，
 * 是因为在Spring Security中我们配置相关参数需要UserDetailsService类型的数据。
 */
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if (user == null) {
            System.out.println(s+":用户名不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }

}
