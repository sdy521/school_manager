package com.study.study_manager.security.service;

import com.study.study_manager.dao.RolesDao;
import com.study.study_manager.dao.UserDao;
import com.study.study_manager.security.entity.MGrantedAuthority;
import com.study.study_manager.entity.Role;
import com.study.study_manager.entity.User;
import com.study.study_manager.security.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Resource
    private RolesDao rolesDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        User select = new User();
        select.setName(username);
        User user = userDao.selectOne(select);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在：" + username);
        }
        List<Role> roleList = rolesDao.getRoles(user.getId());
        Set<MGrantedAuthority> authorities = new HashSet<MGrantedAuthority>();
        if (roleList.size() != 0){
            for (Role role: roleList){
                authorities.add(new MGrantedAuthority(role.getName()));
                System.out.println(role.getName());
            }
        }
        UserDetail userDetail = new UserDetail(user.getName(),user.getPassword(),user.getEnable(),true,true,true,authorities);
        return userDetail;
    }

}
