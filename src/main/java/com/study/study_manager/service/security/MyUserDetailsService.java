package com.study.study_manager.service.security;

import com.study.study_manager.dao.SysRolesDao;
import com.study.study_manager.dao.SysUserRepository;
import com.study.study_manager.entity.security.SysRole;
import com.study.study_manager.entity.security.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysRolesDao sysRolesDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        SysUser sysUser = userRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("该用户不存在：" + username);
        }
        List<SysRole> roles = sysRolesDao.getRoles(sysUser.getId());
        return new SysUser(sysUser.getUsername(),sysUser.getPassword(),roles);
    }

}
