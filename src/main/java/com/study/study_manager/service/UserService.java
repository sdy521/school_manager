package com.study.study_manager.service;

import com.study.study_manager.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUserByName(String username){
        return new User();
    }
}
