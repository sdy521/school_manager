package com.study.study_manager.security.service;

import com.study.study_manager.security.dao.SysRolesDao;
import com.study.study_manager.security.dao.SysUserRepository;
import com.study.study_manager.security.entity.SysRole;
import com.study.study_manager.security.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysRolesDao sysRolesDao;
    @Resource
    private SessionRegistry sessionRegistry;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        SysUser sysUser = userRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("该用户不存在：" + username);
        }
        //用户已经登录则此次登录失效
        /*List<Object> o = sessionRegistry.getAllPrincipals();
        for ( Object principal : o) {
            if (principal instanceof SysUser && (sysUser.getUsername().equals(((SysUser) principal).getUsername()))) {
                throw new SessionAuthenticationException("当前用户已经在线，登录失败！！！");
            }
        }*/
        List<SysRole> roles = sysRolesDao.getRoles(sysUser.getId());
        return new SysUser(sysUser.getUsername(),sysUser.getPassword(),roles);
    }

}
