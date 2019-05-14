package com.study.school_manager;

import com.study.school_manager.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyManagerApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    public void contextLoads() {
        System.out.println(BCrypt.hashpw("sdy",BCrypt.gensalt()));
//        System.out.println(TestEnum.SECRECY.getValue());
    }

    /***
     * 内存流
     */
    @Test
    public void byteArrayIo(){
        String content = "QWER";
        ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b=0;
        while((b=bis.read())!=-1){
            char temp = (char)b;
            bos.write(Character.toLowerCase(temp));
        }
        System.out.println(bos.toString());
        try {
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
