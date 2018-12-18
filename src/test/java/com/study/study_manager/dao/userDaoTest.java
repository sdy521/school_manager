package com.study.study_manager.dao;

import com.study.study_manager.StudyManagerApplicationTests;
import com.study.study_manager.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyManagerApplicationTests.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class userDaoTest {

    @Autowired
    private UserDao userDao;
    @Test
    public void selectAllUser() {
        /*List<User> userList = userDao.selectAllUser();
        assertEquals(1,userList.size());*/
        System.out.println("test");
    }
}