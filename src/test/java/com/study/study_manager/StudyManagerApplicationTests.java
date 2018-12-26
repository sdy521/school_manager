package com.study.study_manager;

import com.study.study_manager.dao.mysql.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyManagerApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    public void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

}
