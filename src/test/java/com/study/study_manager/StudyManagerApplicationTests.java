package com.study.study_manager;

import com.study.study_manager.dao.SysUserRepository;
import com.study.study_manager.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyManagerApplicationTests {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Test
    public void contextLoads() {
        String password = "root";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
        SysUser sysUser = sysUserRepository.findByUsername("root");
//        System.out.println(new BCryptPasswordEncoder(Integer.parseInt(sysUser.getPassword())));
    }

}
