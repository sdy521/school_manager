package com.study.study_manager.security;

import com.study.study_manager.entity.User;
import com.study.study_manager.entity.role;
import com.study.study_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)//只读事务，可以提高效率
@Service
public class CustomUserDetailsService implements UserDetailsService {//（MyAuthenticationProvider需要调用）

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByName(username);
            if(null==user){
                throw new UsernameNotFoundException("UserName "+username+" not found");
            }
         // SecurityUser实现UserDetails并将SUser的Email映射为username
        /*List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (role ur : user.getRoleBeans()) {
            String name = ur.getRoleName();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(name);
            authorities.add(grantedAuthority);
        }
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                authorities);*/
            return new User(
                    user.getUsername(),
                    user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
